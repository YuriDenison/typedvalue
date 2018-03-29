package io.denison.typedvalue.sample.ui.animation

import io.denison.typedvalue.sample.mvp.presenter.BasePresenter
import io.denison.typedvalue.sample.preferences.AppPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class AnimationPresenter @Inject constructor(
    private val appPreferences: AppPreferences
) : BasePresenter<AnimationView>() {

  override fun attachView(view: AnimationView) {
    super.attachView(view)

    appPreferences.animationTypeValue.asObservable().startWith(appPreferences.animationTypeValue.get())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeUntilDetach(onNext = view::bindTo, onError = Timber::e)

    appPreferences.scaleValue.asObservable().startWith(appPreferences.scaleValue.get())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeUntilDetach(onNext = view::bindTo, onError = Timber::e)

  }
}