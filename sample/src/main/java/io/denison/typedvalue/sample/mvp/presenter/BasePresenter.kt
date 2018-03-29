package io.denison.typedvalue.sample.mvp.presenter

import android.support.annotation.CallSuper
import android.support.annotation.UiThread
import io.denison.typedvalue.sample.mvp.MvpPresenter
import io.denison.typedvalue.sample.mvp.MvpView
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.OnErrorNotImplementedException
import java.lang.ref.WeakReference

open class BasePresenter<V : MvpView> : MvpPresenter<V> {
  private lateinit var viewRef: WeakReference<V>

  private val viewDisposables: CompositeDisposable = CompositeDisposable()

  protected val view: V?
    get() = viewRef.get()

  @CallSuper
  @UiThread
  override fun attachView(view: V) {
    viewRef = WeakReference(view)
  }

  @CallSuper
  @UiThread
  override fun detachView() {
    viewDisposables.clear()
    viewRef.clear()
  }

  protected fun <T : Any> Observable<T>.subscribeUntilDetach(
      onNext: (T) -> Unit = {},
      onError: (Throwable) -> Unit = { throw OnErrorNotImplementedException(it) },
      onComplete: () -> Unit = {}
  ): Disposable = subscribe(onNext, onError, onComplete).apply { viewDisposables.add(this) }

  protected fun <T : Any> Maybe<T>.subscribeUntilDetach(
      onSuccess: (T) -> Unit = {},
      onError: (Throwable) -> Unit = { throw OnErrorNotImplementedException(it) },
      onComplete: () -> Unit = {}
  ): Disposable = subscribe(onSuccess, onError, onComplete).apply { viewDisposables.add(this) }

  protected fun <T : Any> Single<T>.subscribeUntilDetach(
      onSuccess: (T) -> Unit = {},
      onError: (Throwable) -> Unit = { throw OnErrorNotImplementedException(it) }
  ): Disposable = subscribe(onSuccess, onError).apply { viewDisposables.add(this) }

  protected fun Completable.subscribeUntilDetach(
      onComplete: () -> Unit = {},
      onError: (Throwable) -> Unit = { throw OnErrorNotImplementedException(it) }
  ): Disposable = subscribe(onComplete, onError).apply { viewDisposables.add(this) }
}
