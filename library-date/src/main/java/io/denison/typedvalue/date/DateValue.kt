package io.denison.typedvalue.date

import io.denison.typedvalue.KeyValueDelegate
import io.denison.typedvalue.TypedValue
import java.util.Date

class DateValue @JvmOverloads constructor(delegate: KeyValueDelegate, key: String, defaultValue: Date = Date()) : TypedValue<Date>(delegate, key, defaultValue) {
  override fun get(): Date = Date(delegate.getLong(key, 0))
  override fun set(value: Date) = delegate.putLong(key, value.time)
}