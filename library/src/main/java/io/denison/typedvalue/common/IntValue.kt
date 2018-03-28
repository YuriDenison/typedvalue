package io.denison.typedvalue.common

import io.denison.typedvalue.KeyValueDelegate
import io.denison.typedvalue.TypedValue

open class IntValue @JvmOverloads constructor(delegate: KeyValueDelegate, key: String, defaultValue: Int = IntValue.EMPTY)
  : TypedValue<Int>(delegate, key, defaultValue) {

  override fun get() = delegate.getInt(key, defaultValue)
  override fun set(value: Int) = delegate.putInt(key, value)

  fun increment() = set(get() + 1)
  fun decrement() = set(get() - 1)

  companion object {
    const val EMPTY = 0
  }
}
