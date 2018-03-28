package io.denison.typedvalue.common

import io.denison.typedvalue.KeyValueDelegate
import io.denison.typedvalue.TypedValue

class StringSetValue(delegate: KeyValueDelegate, key: String, defaultValue: Set<String> = emptySet()) : TypedValue<Set<String>>(delegate, key, defaultValue) {

  override fun get() = delegate.getStringSet(key, defaultValue)
  override fun set(value: Set<String>) = delegate.putStringSet(key, value)
}
