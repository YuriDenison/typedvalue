package io.denison.typedvalue.delegate

import android.content.SharedPreferences
import android.os.Parcelable
import io.denison.typedvalue.KeyValueDelegate
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import java.lang.Double.doubleToLongBits
import java.lang.Double.longBitsToDouble

class PreferenceDelegate(private val prefs: SharedPreferences) : KeyValueDelegate {
  private val keyChangesObservable: Observable<String>

  init {
    keyChangesObservable = io.reactivex.Observable.create(ObservableOnSubscribe<String> { emitter ->
      val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key -> emitter.onNext(key) }
      emitter.setCancellable { prefs.unregisterOnSharedPreferenceChangeListener(listener) }
      prefs.registerOnSharedPreferenceChangeListener(listener)
    }).share()
  }

  override fun getInt(key: String, defaultValue: Int) = prefs.getInt(key, defaultValue)
  override fun putInt(key: String, value: Int) = prefs.edit().putInt(key, value).apply()

  override fun getString(key: String, defaultValue: String): String = prefs.getString(key, defaultValue)
  override fun putString(key: String, value: String) = prefs.edit().putString(key, value).apply()

  override fun getBoolean(key: String, defaultValue: Boolean) = prefs.getBoolean(key, defaultValue)
  override fun putBoolean(key: String, value: Boolean) = prefs.edit().putBoolean(key, value).apply()

  override fun getLong(key: String, defaultValue: Long) = prefs.getLong(key, defaultValue)
  override fun putLong(key: String, value: Long) = prefs.edit().putLong(key, value).apply()

  override fun getDouble(key: String, defaultValue: Double) = longBitsToDouble(prefs.getLong(key, doubleToLongBits(defaultValue)))
  override fun putDouble(key: String, value: Double) = prefs.edit().putLong(key, doubleToLongBits(value)).apply()

  override fun getFloat(key: String, defaultValue: Float) = prefs.getFloat(key, defaultValue)
  override fun putFloat(key: String, value: Float) = prefs.edit().putFloat(key, value).apply()

  override fun getStringSet(key: String, defaultValue: Set<String>): Set<String> = prefs.getStringSet(key, defaultValue)
  override fun putStringSet(key: String, value: Set<String>) = prefs.edit().putStringSet(key, value).apply()

  override fun getStringList(key: String, defaultValue: List<String>) = throw IllegalArgumentException("Unsupported type 'List<String>'")
  override fun putStringList(key: String, value: List<String>) = throw IllegalArgumentException("Unsupported type 'List<String>'")

  override fun <T : Parcelable> getParcelable(key: String, defaultValue: T): T = throw IllegalArgumentException("Unsupported type 'Parcelable'")
  override fun <T : Parcelable> putParcelable(key: String, value: T) = throw IllegalArgumentException("Unsupported type 'Parcelable'")

  override operator fun contains(key: String) = prefs.contains(key)

  override fun remove(key: String) = prefs.edit().remove(key).apply()

  override fun clear() = prefs.edit().clear().apply()

  override fun keyChanges(): Observable<String> = keyChangesObservable
}
