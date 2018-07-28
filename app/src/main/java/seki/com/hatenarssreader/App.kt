package seki.com.hatenarssreader

import android.app.Application
import seki.com.hatenarssreader.di.DaggerApplicationComponent

class App: Application() {
    val appComponent = DaggerApplicationComponent.create()!!
}