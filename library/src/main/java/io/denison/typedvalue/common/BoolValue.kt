package io.denison.typedvalue.common

import io.denison.typedvalue.KeyValueDelegate
import io.denison.typedvalue.TypedValue

class BoolValue @JvmOverloads constructor(delegate: KeyValueDelegate, key: String, defaultValue: Boolean = EMPTY)
  : TypedValue<Boolean>(delegate, key, defaultValue) {

  override fun get() = delegate.getBoolean(key, defaultValue)
  override fun set(value: Boolean) = delegate.putBoolean(key, value)

  fun toggle() = set(!get())

  companion object {
    const val EMPTY = false
  }
}
