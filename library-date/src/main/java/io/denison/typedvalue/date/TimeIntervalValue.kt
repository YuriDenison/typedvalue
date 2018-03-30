package io.denison.typedvalue.date

import io.denison.typedvalue.KeyValueDelegate
import io.denison.typedvalue.common.LongValue

class TimeIntervalValue(delegate: KeyValueDelegate, key: String) : LongValue(delegate, key) {
  private var duration = 0L

  fun trackStart() = set(System.currentTimeMillis())
  fun trackEnd() {
    duration += System.currentTimeMillis() - get()
  }

  fun getMillis(): Long = duration
  fun getSeconds(): Long = duration / 1000
}