package io.denison.typedvalue.delegate

import android.os.Parcelable
import io.denison.typedvalue.KeyValueDelegate
import io.reactivex.Observable
import java.util.concurrent.ConcurrentHashMap

class MapDelegate(private val map: MutableMap<String, Any> = ConcurrentHashMap()) : KeyValueDelegate {
  override fun getInt(key: String, defaultValue: Int): Int = map[key] as Int? ?: defaultValue
  override fun putInt(key: String, value: Int) {
    map[key] = value
  }

  override fun getBoolean(key: String, defaultValue: Boolean) = map[key] as Boolean? ?: defaultValue
  override fun putBoolean(key: String, value: Boolean) {
    map[key] = value
  }

  override fun getLong(key: String, defaultValue: Long) = map[key] as Long? ?: defaultValue
  override fun putLong(key: String, value: Long) {
    map[key] = value
  }

  override fun getString(key: String, defaultValue: String) = map[key] as String? ?: defaultValue
  override fun putString(key: String, value: String) {
    map[key] = value
  }

  override fun getDouble(key: String, defaultValue: Double) = map[key] as Double? ?: defaultValue
  override fun putDouble(key: String, value: Double) {
    map[key] = value
  }

  override fun getFloat(key: String, defaultValue: Float) = map[key] as Float? ?: defaultValue
  override fun putFloat(key: String, value: Float) {
    map[key] = value
  }

  @Suppress("UNCHECKED_CAST")
  override fun getStringSet(key: String, defaultValue: Set<String>): Set<String> = map[key] as Set<String>? ?: defaultValue

  override fun putStringSet(key: String, value: Set<String>) {
    map[key] = value
  }

  @Suppress("UNCHECKED_CAST")
  override fun getStringList(key: String, defaultValue: List<String>) = map[key] as List<String>? ?: defaultValue

  override fun putStringList(key: String, value: List<String>) {
    map[key] = value
  }

  @Suppress("UNCHECKED_CAST")
  override fun <T : Parcelable> getParcelable(key: String, defaultValue: T): T = map[key] as T? ?: defaultValue

  override fun <T : Parcelable> putParcelable(key: String, value: T) {
    map[key] = value
  }

  override fun contains(key: String): Boolean = map.contains(key)

  override fun remove(key: String) {
    map.remove(key)
  }

  override fun clear() = map.clear()

  override fun keyChanges(): Observable<String> = Observable.empty()
}