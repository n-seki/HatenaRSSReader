package seki.com.hatenarssreader.client

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import seki.com.hatenarssreader.data.Result

interface HatenaService {

    companion object {
        const val BASE_URL = "http://b.hatena.ne.jp/"
    }

    @GET("hotentry.rss")
    fun fetchHotEntry(): Call<Result>

    @GET("hotentry/{category}.rss")
    fun fetchHotEntry(@Path("category") category: String): Call<Result>
}