package io.denison.typedvalue.sample.ui.animation

import io.denison.typedvalue.sample.model.AnimationType
import io.denison.typedvalue.sample.mvp.MvpView

interface AnimationView : MvpView {
  fun bindTo(type: AnimationType)
  fun bindTo(scale: Float)
}