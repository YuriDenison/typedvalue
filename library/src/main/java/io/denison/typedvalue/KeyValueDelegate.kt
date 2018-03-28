package io.denison.typedvalue

import android.os.Parcelable
import io.reactivex.Observable

interface KeyValueDelegate {

  fun getInt(key: String, defaultValue: Int): Int
  fun putInt(key: String, value: Int)

  fun getBoolean(key: String, defaultValue: Boolean): Boolean
  fun putBoolean(key: String, value: Boolean)

  fun getLong(key: String, defaultValue: Long): Long
  fun putLong(key: String, value: Long)

  fun getString(key: String, defaultValue: String): String
  fun putString(key: String, value: String)

  fun getDouble(key: String, defaultValue: Double): Double
  fun putDouble(key: String, value: Double)

  fun getFloat(key: String, defaultValue: Float): Float
  fun putFloat(key: String, value: Float)

  fun getStringSet(key: String, defaultValue: Set<String>): Set<String>
  fun putStringSet(key: String, value: Set<String>)

  fun getStringList(key: String, defaultValue: List<String>): List<String>
  fun putStringList(key: String, value: List<String>)

  fun <T : Parcelable> getParcelable(key: String, defaultValue: T): T
  fun <T : Parcelable> putParcelable(key: String, value: T)

  operator fun contains(key: String): Boolean

  fun remove(key: String)

  fun clear()

  fun keyChanges(): Observable<String>
}
