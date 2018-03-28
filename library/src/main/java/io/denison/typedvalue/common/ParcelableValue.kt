package io.denison.typedvalue.common

import android.os.Parcelable
import io.denison.typedvalue.KeyValueDelegate
import io.denison.typedvalue.TypedValue

class ParcelableValue<T : Parcelable>(delegate: KeyValueDelegate, key: String, defaultValue: T) : TypedValue<T>(delegate, key, defaultValue) {

  override fun get() = delegate.getParcelable<T>(key)
  override fun set(value: T) = delegate.putParcelable(key, value)
}
