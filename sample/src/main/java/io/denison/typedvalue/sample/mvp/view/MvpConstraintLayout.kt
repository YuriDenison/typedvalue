package io.denison.typedvalue.sample.mvp.view

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import io.denison.typedvalue.sample.mvp.MvpPresenter
import io.denison.typedvalue.sample.mvp.MvpView

abstract class MvpConstraintLayout<V : MvpView, out P : MvpPresenter<V>>(context: Context, attrs: AttributeSet?)
  : ConstraintLayout(context, attrs), MvpView {

  protected val presenter: P by lazy { createPresenter() }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    if (!isInEditMode) {
      presenter.attachView(getMvpView())
    }
  }

  override fun onDetachedFromWindow() {
    if (!isInEditMode) {
      presenter.detachView()
    }
    super.onDetachedFromWindow()
  }

  @Suppress("UNCHECKED_CAST")
  open fun getMvpView() = this as V

  abstract fun createPresenter(): P
}
