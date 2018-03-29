package io.denison.typedvalue.sample.ui.animation

import dagger.Component
import io.denison.typedvalue.sample.di.AppComponent
import io.denison.typedvalue.sample.di.ViewScope

@ViewScope
@Component(dependencies = [AppComponent::class])
interface AnimationComponent {
  fun presenter(): AnimationPresenter
}