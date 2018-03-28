package io.denison.typedvalue.common

import io.denison.typedvalue.KeyValueDelegate
import io.denison.typedvalue.TypedValue

class FloatValue @JvmOverloads constructor(delegate: KeyValueDelegate, key: String, defaultValue: Float = FloatValue.EMPTY)
  : TypedValue<Float>(delegate, key, defaultValue) {

  override fun get() = delegate.getFloat(key, defaultValue)
  override fun set(value: Float) = delegate.putFloat(key, value)

  companion object {
    const val EMPTY = 0.0f
  }
}
