package io.denison.typedvalue.sample.mvp

import android.support.annotation.UiThread

interface MvpPresenter<in V : MvpView> {
  @UiThread fun attachView(view: V)
  @UiThread fun detachView()
}