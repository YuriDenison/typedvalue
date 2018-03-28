package io.denison.typedvalue.common

import io.denison.typedvalue.KeyValueDelegate
import io.denison.typedvalue.TypedValue

open class StringValue @JvmOverloads constructor(delegate: KeyValueDelegate, key: String, defaultValue: String = StringValue.EMPTY)
  : TypedValue<String>(delegate, key, defaultValue) {

  override fun get() = delegate.getString(key, defaultValue)
  override fun set(value: String) = delegate.putString(key, value)

  override fun isSet() = delegate.contains(key) && EMPTY != get()

  companion object {
    const val EMPTY = ""
  }
}
