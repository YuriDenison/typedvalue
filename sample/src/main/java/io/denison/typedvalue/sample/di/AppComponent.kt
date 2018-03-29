package io.denison.typedvalue.sample.di

import dagger.Component
import io.denison.typedvalue.sample.preferences.AppPreferences
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
  fun preferences(): AppPreferences
}