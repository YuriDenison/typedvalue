package io.denison.typedvalue.date

import io.denison.typedvalue.KeyValueDelegate
import io.denison.typedvalue.TypedValue
import org.threeten.bp.Clock
import org.threeten.bp.Instant
import org.threeten.bp.LocalDate

class LocalDateValue @JvmOverloads constructor(delegate: KeyValueDelegate, key: String, defaultValue: LocalDate = LocalDate.now()) : TypedValue<LocalDate>(delegate, key, defaultValue) {
  override fun get(): LocalDate = Instant.ofEpochMilli(delegate.getLong(key, 0)).atZone(Clock.systemUTC().zone).toLocalDate()
  override fun set(value: LocalDate) = delegate.putLong(key, value.atStartOfDay(Clock.systemUTC().zone).toInstant().toEpochMilli())
}