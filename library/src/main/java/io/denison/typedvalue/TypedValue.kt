package io.denison.typedvalue

import io.reactivex.Observable
import io.reactivex.functions.Consumer

abstract class TypedValue<T> protected constructor(protected val delegate: KeyValueDelegate, val key: String, protected val defaultValue: T) {

  abstract fun get(): T
  abstract fun set(value: T)

  fun remove() = delegate.remove(key)

  open fun isSet(): Boolean = delegate.contains(key)

  fun asObservable(): Observable<T> = delegate.keyChanges()
      .filter { k -> k == key }
      .startWith("<init>")
      .map { get() }

  fun asConsumer(): Consumer<T> = Consumer { set(it) }
}
