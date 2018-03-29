package io.denison.typedvalue.sample

import android.app.Application
import android.content.Context
import io.denison.typedvalue.sample.di.AppComponent
import io.denison.typedvalue.sample.di.AppModule
import io.denison.typedvalue.sample.di.DaggerAppComponent

class SampleApplication : Application() {
  private lateinit var component: AppComponent

  override fun onCreate() {
    super.onCreate()

    component = DaggerAppComponent.builder()
        .appModule(AppModule(this))
        .build()
  }

  companion object {
    fun appComponent(context: Context): AppComponent = (context.applicationContext as SampleApplication).component
  }
}