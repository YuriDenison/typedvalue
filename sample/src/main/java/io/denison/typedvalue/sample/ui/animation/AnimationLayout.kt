package io.denison.typedvalue.sample.ui.animation

import android.content.Context
import android.util.AttributeSet
import io.denison.typedvalue.sample.R
import io.denison.typedvalue.sample.SampleApplication
import io.denison.typedvalue.sample.model.AnimationType
import io.denison.typedvalue.sample.mvp.view.MvpConstraintLayout
import kotlinx.android.synthetic.main.ac_main.view.lottieView

class AnimationLayout(context: Context, attributeSet: AttributeSet) : MvpConstraintLayout<AnimationView, AnimationPresenter>(context, attributeSet), AnimationView {
  private lateinit var component: AnimationComponent

  override fun onAttachedToWindow() {
    injectDependencies()
    super.onAttachedToWindow()
  }

  override fun bindTo(type: AnimationType) {
    lottieView.setAnimation(when (type) {
      AnimationType.FAVORITE -> R.raw.favourite
      AnimationType.CONFETTI -> R.raw.confetti
      AnimationType.PLANE    -> R.raw.plane
    })
    lottieView.playAnimation()
  }

  override fun bindTo(scale: Float) {
    lottieView.scale = scale
  }


  override fun createPresenter(): AnimationPresenter = component.presenter()

  private fun injectDependencies() {
    if (isInEditMode)
      return

    component = DaggerAnimationComponent.builder()
        .appComponent(SampleApplication.appComponent(context))
        .build()
  }
}