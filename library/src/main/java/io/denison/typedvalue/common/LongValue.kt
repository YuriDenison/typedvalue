package io.denison.typedvalue.common

import io.denison.typedvalue.KeyValueDelegate
import io.denison.typedvalue.TypedValue

open class LongValue @JvmOverloads constructor(delegate: KeyValueDelegate, key: String, defaultValue: Long = LongValue.EMPTY)
  : TypedValue<Long>(delegate, key, defaultValue) {

  override fun get() = delegate.getLong(key, defaultValue)
  override fun set(value: Long) = delegate.putLong(key, value)

  companion object {
    const val EMPTY = 0L
  }
}
