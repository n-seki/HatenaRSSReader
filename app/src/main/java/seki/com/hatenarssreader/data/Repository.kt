package seki.com.hatenarssreader.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import seki.com.hatenarssreader.client.HatenaService
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject

class Repository @Inject constructor(private val hatanaService: HatenaService) {

    private val cache: MutableMap<String, List<RssItem>> = ConcurrentHashMap()

    fun getHotEntry(category: String, forceFetch: Boolean): LiveData<List<RssItem>> {
        if (forceFetch || category !in cache.keys) {
            return fetchHotEntry(category)
        }

        val data = MutableLiveData<List<RssItem>>()
        return data.apply { postValue(cache.getValue(category)) }
    }

    private fun fetchHotEntry(category: String): LiveData<List<RssItem>> {
        val call: Call<Result> =
                if (category != "all") {
                    hatanaService.fetchHotEntry(category)
                } else {
                    hatanaService.fetchHotEntry()
                }

        val rssItemList = MutableLiveData<List<RssItem>>()

        call.enqueue(object : Callback<Result> {
            override fun onFailure(call: Call<Result>?, t: Throwable?) {
                rssItemList.postValue(listOf())
            }

            override fun onResponse(call: Call<Result>?, response: Response<Result>?) {
                val result = response?.body()?.item

                if (result == null) {
                    rssItemList.postValue(listOf())
                    return
                }

                cache += category to result
                rssItemList.postValue(result)
            }
        })

        return rssItemList
    }
}