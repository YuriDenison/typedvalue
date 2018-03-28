package io.denison.typedvalue.common

import io.denison.typedvalue.KeyValueDelegate
import io.denison.typedvalue.TypedValue

class StringListValue @JvmOverloads constructor(delegate: KeyValueDelegate, key: String, defaultValue: List<String> = emptyList()) : TypedValue<List<String>>(delegate, key, defaultValue) {

  override fun get() = delegate.getStringList(key, defaultValue)
  override fun set(value: List<String>) = delegate.putStringList(key, value)
}
