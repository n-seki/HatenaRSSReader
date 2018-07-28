package seki.com.hatenarssreader

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import seki.com.hatenarssreader.data.Repository
import seki.com.hatenarssreader.data.RssItem
import javax.inject.Inject

class RssListViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _rssListData: MutableLiveData<List<RssItem>> = MutableLiveData()
    val rssListData: LiveData<List<RssItem>> = _rssListData

    fun init(category: String, forceFetch: Boolean = false) {
        repository.getHotEntry(category, forceFetch,  object : Repository.FetchCallbackListener {
            override fun onFailFetch() {
                _rssListData.postValue(listOf())
            }

            override fun onSuccessFetch(hotEntryList: List<RssItem>) {
                _rssListData.postValue(hotEntryList)
            }
        })
    }
}