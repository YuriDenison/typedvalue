package io.denison.typedvalue.sample.ui.settings

import io.denison.typedvalue.sample.model.AnimationType
import io.denison.typedvalue.sample.mvp.presenter.BasePresenter
import io.denison.typedvalue.sample.preferences.AppPreferences
import io.denison.typedvalue.sample.ui.settings.SettingsView.ViewAction
import io.denison.typedvalue.sample.ui.settings.SettingsView.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class SettingsPresenter @Inject constructor(
    private val appPreferences: AppPreferences
) : BasePresenter<SettingsView>() {

  override fun attachView(view: SettingsView) {
    super.attachView(view)

    Observables.combineLatest(
        appPreferences.animationTypeValue.asObservable().startWith(appPreferences.animationTypeValue.get()),
        appPreferences.scaleValue.asObservable().startWith(appPreferences.scaleValue.get()),
        { type, scale -> ViewModel.Content(type, (scale * 100).toInt()) }
    )
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeUntilDetach(onNext = view::bindTo, onError = Timber::e)

    view.observeViewActions()
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribeUntilDetach(onNext = this::handleViewAction, onError = Timber::e)

    if (appPreferences.messageShownValue.get()) {
      appPreferences.messageShownValue.set(false)
      view.showWelcomeMessage()
    }
  }

  private fun handleViewAction(action: ViewAction) = when (action) {
    is ViewAction.ScaleChanged         -> appPreferences.scaleValue.set(action.value / 100f)
    is ViewAction.AnimationTypeClicked -> appPreferences.animationTypeValue.set(when (action.type) {
      AnimationType.CONFETTI -> AnimationType.PLANE
      AnimationType.PLANE    -> AnimationType.FAVORITE
      AnimationType.FAVORITE -> AnimationType.CONFETTI
    })
  }
}