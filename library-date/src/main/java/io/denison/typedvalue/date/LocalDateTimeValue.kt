package io.denison.typedvalue.date

import io.denison.typedvalue.KeyValueDelegate
import io.denison.typedvalue.TypedValue
import org.threeten.bp.Clock
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset

class LocalDateTimeValue @JvmOverloads constructor(delegate: KeyValueDelegate, key: String, defaultValue: LocalDateTime = LocalDateTime.now()) : TypedValue<LocalDateTime>(delegate, key, defaultValue) {
  override fun get(): LocalDateTime = Instant.ofEpochMilli(delegate.getLong(key, 0)).atZone(Clock.systemUTC().zone).toLocalDateTime()
  override fun set(value: LocalDateTime) = delegate.putLong(key, value.toInstant(ZoneOffset.UTC).toEpochMilli())
}