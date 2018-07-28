package seki.com.hatenarssreader.client

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import seki.com.hatenarssreader.data.Result
import seki.com.hatenarssreader.data.RssItem

interface HatenaService {

    companion object {
        const val BASE_URL = "http://b.hatena.ne.jp/"
    }

    @GET("hotentry.rss")
    fun fetchHotEntory(): Call<Result>

    @GET("hotentry/{category}.rss")
    fun fetchHotEntry(@Path("category") category: String): Call<Result>
}