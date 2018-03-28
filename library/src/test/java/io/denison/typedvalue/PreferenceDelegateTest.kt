package io.denison.typedvalue

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import io.denison.typedvalue.delegate.PreferenceDelegate
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(RobolectricTestRunner::class)
class PreferenceDelegateTest {
  private lateinit var sharedPreferences: SharedPreferences
  private lateinit var delegate: PreferenceDelegate

  @Before
  fun setUp() {
    sharedPreferences = PreferenceManager.getDefaultSharedPreferences(RuntimeEnvironment.application).apply {
      edit()
          .putInt(KEY_INT_DEFAULT, 1)
          .putBoolean(KEY_BOOLEAN_DEFAULT, true)
          .putLong(KEY_LONG_DEFAULT, 1)
          .putString(KEY_STRING_DEFAULT, "default")
          .putFloat(KEY_FLOAT_DEFAULT, 1f)
          .putStringSet(KEY_STRING_SET_DEFAULT, setOf("default"))
          .commit()
    }
    delegate = PreferenceDelegate(sharedPreferences)
  }

  @Test
  fun testGetInt() {
    assertEquals(1, delegate.getInt(KEY_INT_DEFAULT, 2))
    assertEquals(2, delegate.getInt(KEY_INT, 2))
  }

  @Test
  fun testPutInt() {
    delegate.putInt(KEY_INT, 1)
    assertEquals(1, sharedPreferences.getInt(KEY_INT, 0))
  }

  @Test
  fun testGetBoolean() {
    assertTrue { delegate.getBoolean(KEY_BOOLEAN_DEFAULT, false) }
    assertFalse { delegate.getBoolean(KEY_BOOLEAN, false) }
  }

  @Test
  fun testPutBoolean() {
    delegate.putBoolean(KEY_BOOLEAN, true)
    assertEquals(true, sharedPreferences.getBoolean(KEY_BOOLEAN, false))
  }

  @Test
  fun testGetLong() {
    assertEquals(1, delegate.getLong(KEY_LONG_DEFAULT, 2))
    assertEquals(2, delegate.getLong(KEY_LONG, 2))
  }

  @Test
  fun testPutLong() {
    delegate.putLong(KEY_LONG, 1)
    assertEquals(1, sharedPreferences.getLong(KEY_LONG, 0))
  }

  @Test
  fun testGetString() {
    assertEquals("default", delegate.getString(KEY_STRING_DEFAULT, "val"))
    assertEquals("val", delegate.getString(KEY_STRING, "val"))
  }

  @Test
  fun testPutString() {
    delegate.putString(KEY_STRING, "val")
    assertEquals("val", sharedPreferences.getString(KEY_STRING, ""))
  }

  @Test
  fun testGetDouble() {
    assertEquals(2.0, delegate.getDouble(KEY_DOUBLE, 2.0))
  }

  @Test
  fun testPutDouble() {
    delegate.putDouble(KEY_DOUBLE, 2.0)
    assertEquals(2.0, delegate.getDouble(KEY_DOUBLE, 0.0))
  }

  @Test
  fun testGetFloat() {
    assertEquals(1f, delegate.getFloat(KEY_FLOAT_DEFAULT, 2f))
    assertEquals(2f, delegate.getFloat(KEY_FLOAT, 2f))
  }

  @Test
  fun testPutFloat() {
    delegate.putFloat(KEY_FLOAT, 2f)
    assertEquals(2f, sharedPreferences.getFloat(KEY_FLOAT, 0f))
  }

  @Test
  fun testGetStringSet() {
    val set = setOf("val")
    assertEquals(setOf("default"), delegate.getStringSet(KEY_STRING_SET_DEFAULT, emptySet()))
    assertEquals(set, delegate.getStringSet(KEY_STRING_SET, set))
  }

  @Test
  fun testPutStringSet() {
    delegate.putStringSet(KEY_STRING_SET, setOf("val"))
    assertEquals(setOf("val"), sharedPreferences.getStringSet(KEY_STRING_SET, emptySet()))
  }

  @Test
  fun testGetStringList() {
    assertFails { delegate.getStringList(KEY_STRING_LIST, listOf()) }
  }

  @Test
  fun testPutStringList() {
    assertFails { delegate.putStringList(KEY_STRING_LIST, listOf()) }
  }

  @Test
  fun testGetParcelable() {
    assertFails { delegate.getParcelable(KEY_PARCELABLE, Bundle()) }
  }

  @Test
  fun testPutParcelable() {
    assertFails { delegate.putParcelable(KEY_PARCELABLE, Bundle()) }
  }

  @Test
  fun testContains() {
    assertTrue { KEY_INT_DEFAULT in delegate }
    assertTrue { KEY_INT !in delegate }
  }

  @Test
  fun testRemove() {
    delegate.remove(KEY_INT_DEFAULT)
    assertTrue { KEY_INT_DEFAULT !in delegate }
    assertFalse { sharedPreferences.contains(KEY_INT_DEFAULT) }
  }

  @Test
  fun testClear() {
    delegate.clear()
    assertTrue { KEY_INT_DEFAULT !in delegate }
    assertTrue { sharedPreferences.all.isEmpty() }
  }


  private companion object {
    const val KEY_INT = ".KEY_INT"
    const val KEY_INT_DEFAULT = ".KEY_INT_DEFAULT"
    const val KEY_BOOLEAN = "KEY_BOOLEAN"
    const val KEY_BOOLEAN_DEFAULT = "KEY_BOOLEAN_DEFAULT"
    const val KEY_LONG = "KEY_LONG"
    const val KEY_LONG_DEFAULT = "KEY_LONG_DEFAULT"
    const val KEY_STRING = "KEY_STRING"
    const val KEY_STRING_DEFAULT = "KEY_STRING_DEFAULT"
    const val KEY_DOUBLE = "KEY_DOUBLE"
    const val KEY_FLOAT = "KEY_FLOAT"
    const val KEY_FLOAT_DEFAULT = "KEY_FLOAT_DEFAULT"
    const val KEY_STRING_SET_DEFAULT = "KEY_STRING_SET_DEFAULT"
    const val KEY_STRING_SET = "KEY_STRING_SET"
    const val KEY_STRING_LIST = "KEY_STRING_LIST"
    const val KEY_PARCELABLE = "KEY_PARCELABLE"
  }
}