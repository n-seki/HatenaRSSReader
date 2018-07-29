package seki.com.hatenarssreader.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import seki.com.hatenarssreader.client.HatenaService
import seki.com.hatenarssreader.data.Repository
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun provideRepository(hatenaService: HatenaService): Repository {
        return Repository(hatenaService)
    }

    @Singleton
    @Provides
    fun proviceHatanaService(hatenaRetrofit: Retrofit): HatenaService {
        return hatenaRetrofit.create(HatenaService::class.java)
    }

    @Singleton
    @Provides
    fun provideHatanaRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(HatenaService.BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder().addInterceptor(logging).build()
    }
}