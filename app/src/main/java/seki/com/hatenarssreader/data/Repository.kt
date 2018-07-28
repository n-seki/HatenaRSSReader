package seki.com.hatenarssreader.data

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import seki.com.hatenarssreader.client.HatenaService
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject

class Repository @Inject constructor(private val hatanaService: HatenaService) {

    private val cache: MutableMap<String, List<RssItem>> = ConcurrentHashMap()

    interface FetchCallbackListener {
        fun onSuccessFetch(hotEntryList: List<RssItem>)
        fun onFailFetch()
    }

    fun getHotEntry(category: String, forceFetch: Boolean, listener: FetchCallbackListener) {
        if (forceFetch) {
            fetchHotEntry(category, listener)
            return
        }

        val cachedCategory = cache.keys
        when (category) {
            in cachedCategory -> listener.onSuccessFetch(cache[category]!!)
            else -> fetchHotEntry(category, listener)
        }
    }

    private fun fetchHotEntry(category: String, listener: FetchCallbackListener) {
        val call: Call<Result> =
                if (category != "all") {
                    hatanaService.fetchHotEntry(category)
                } else {
                    hatanaService.fetchHotEntory()
                }

        call.enqueue(object : Callback<Result> {
            override fun onFailure(call: Call<Result>?, t: Throwable?) {
                listener.onFailFetch()
            }

            override fun onResponse(call: Call<Result>?, response: Response<Result>?) {
                response?.body() ?: listener.onFailFetch()

                val rssItemList = response?.body()?.item!!
                cache += category to rssItemList
                listener.onSuccessFetch(rssItemList)
            }
        })
    }
}