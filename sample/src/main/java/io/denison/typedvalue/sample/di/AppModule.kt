package io.denison.typedvalue.sample.di

import android.app.Application
import dagger.Module
import dagger.Provides
import io.denison.typedvalue.sample.SampleApplication
import io.denison.typedvalue.sample.preferences.AppPreferences
import io.denison.typedvalue.sample.preferences.AppPreferencesImpl
import javax.inject.Singleton

@Module
class AppModule(private val app: SampleApplication) {
  @Singleton
  @Provides
  fun provideApplication(): Application = app

  @Singleton
  @Provides
  fun provideAppPreferences(app: Application): AppPreferences = AppPreferencesImpl(app)
}