package seki.com.hatenarssreader.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import seki.com.hatenarssreader.data.Repository
import seki.com.hatenarssreader.data.RssItem
import javax.inject.Inject

class RssListViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    // <category, forceFetch>
    private val _fetchInfo: MutableLiveData<Pair<String, Boolean>> = MutableLiveData()
    val rssListData: LiveData<List<RssItem>>

    init {
        rssListData = Transformations.switchMap(_fetchInfo) {
            repository.getHotEntry(it.first, it.second)
        }
    }

    fun init(category: String, forceFetch: Boolean) {
        _fetchInfo.postValue(category to forceFetch)
    }
}