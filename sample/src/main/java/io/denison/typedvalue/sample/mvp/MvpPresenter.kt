package io.denison.typedvalue.sample.mvp

import androidx.annotation.UiThread

interface MvpPresenter<in V : MvpView> {
  @UiThread fun attachView(view: V)
  @UiThread fun detachView()
}