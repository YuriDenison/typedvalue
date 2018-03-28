package io.denison.typedvalue.delegate

import android.os.Bundle
import android.os.Parcelable
import io.denison.typedvalue.KeyValueDelegate
import io.reactivex.Observable
import java.util.ArrayList

class BundleDelegate(private val bundle: Bundle = Bundle()) : KeyValueDelegate {
  override fun getInt(key: String, defaultValue: Int) = bundle.getInt(key, defaultValue)
  override fun putInt(key: String, value: Int) = bundle.putInt(key, value)

  override fun getBoolean(key: String, defaultValue: Boolean) = bundle.getBoolean(key, defaultValue)
  override fun putBoolean(key: String, value: Boolean) = bundle.putBoolean(key, value)

  override fun getLong(key: String, defaultValue: Long) = bundle.getLong(key, defaultValue)
  override fun putLong(key: String, value: Long) = bundle.putLong(key, value)

  override fun getString(key: String, defaultValue: String): String = bundle.getString(key, defaultValue)
  override fun putString(key: String, value: String) = bundle.putString(key, value)

  override fun getDouble(key: String, defaultValue: Double) = bundle.getDouble(key, defaultValue)
  override fun putDouble(key: String, value: Double) = bundle.putDouble(key, value)

  override fun getFloat(key: String, defaultValue: Float) = bundle.getFloat(key, defaultValue)
  override fun putFloat(key: String, value: Float) = bundle.putFloat(key, value)

  override fun getStringSet(key: String): Set<String> = throw IllegalArgumentException("Unsupported type 'Set<String>'")
  override fun putStringSet(key: String, value: Set<String>) = throw IllegalArgumentException("Unsupported type 'Set<String>'")

  override fun getStringList(key: String, defaultValue: List<String>): List<String> = bundle.getStringArrayList(key) ?: defaultValue
  override fun putStringList(key: String, value: List<String>) = bundle.putStringArrayList(key, ArrayList(value))

  override fun <T : Parcelable> getParcelable(key: String): T = bundle.getParcelable(key)
  override fun <T : Parcelable> putParcelable(key: String, value: T) = bundle.putParcelable(key, value)

  override fun remove(key: String) = bundle.remove(key)

  override fun clear() = bundle.clear()

  override fun keyChanges(): Observable<String> = Observable.empty<String>()

  override operator fun contains(key: String) = bundle.containsKey(key)
}
