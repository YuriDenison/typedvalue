package io.denison.typedvalue.common

import io.denison.typedvalue.KeyValueDelegate
import io.denison.typedvalue.TypedValue

class DoubleValue @JvmOverloads constructor(delegate: KeyValueDelegate, key: String, defaultValue: Double = DoubleValue.EMPTY)
  : TypedValue<Double>(delegate, key, defaultValue) {

  override fun get() = delegate.getDouble(key, defaultValue)
  override fun set(value: Double) = delegate.putDouble(key, value)


  companion object {
    const val EMPTY = 0.0
  }
}
