package seki.com.hatenarssreader.di

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import seki.com.hatenarssreader.RssListViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RssListViewModel::class)
    abstract fun bindRssListViewModel(viewModel: RssListViewModel): ViewModel
}