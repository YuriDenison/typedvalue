package io.denison.typedvalue.sample.ui.settings

import dagger.Component
import io.denison.typedvalue.sample.di.AppComponent
import io.denison.typedvalue.sample.di.ViewScope

@ViewScope
@Component(dependencies = [AppComponent::class])
interface SettingsComponent {
  fun presenter(): SettingsPresenter
}