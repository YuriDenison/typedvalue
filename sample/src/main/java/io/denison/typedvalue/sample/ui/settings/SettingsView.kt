package io.denison.typedvalue.sample.ui.settings

import io.denison.typedvalue.sample.model.AnimationType
import io.denison.typedvalue.sample.mvp.MvpView
import io.reactivex.Observable

interface SettingsView : MvpView {
  fun bindTo(model: ViewModel)
  fun showWelcomeMessage()

  fun observeViewActions(): Observable<ViewAction>

  sealed class ViewModel {
    data class Content(val type: AnimationType, val scale: Float) : ViewModel()
  }

  sealed class ViewAction {
    data class AnimationTypeClicked(val type: AnimationType) : ViewAction()
    data class ScaleChanged(val value: Float) : ViewAction()
  }
}