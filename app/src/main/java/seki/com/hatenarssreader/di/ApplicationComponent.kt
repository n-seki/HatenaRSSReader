package seki.com.hatenarssreader.di

import dagger.Component
import seki.com.hatenarssreader.RssListFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(fragment: RssListFragment)
}