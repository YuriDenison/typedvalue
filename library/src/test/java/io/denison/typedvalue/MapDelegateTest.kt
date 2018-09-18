package io.denison.typedvalue

import android.os.Bundle
import io.denison.typedvalue.delegate.MapDelegate
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(RobolectricTestRunner::class)
class MapDelegateTest {
  private lateinit var map: MutableMap<String, Any>
  private lateinit var delegate: MapDelegate

  @Before
  fun setUp() {
    map = mutableMapOf(
        KEY_INT_DEFAULT to 1,
        KEY_BOOLEAN_DEFAULT to true,
        KEY_LONG_DEFAULT to 1L,
        KEY_STRING_DEFAULT to "default",
        KEY_DOUBLE_DEFAULT to 1.0,
        KEY_FLOAT_DEFAULT to 1f,
        KEY_STRING_LIST_DEFAULT to arrayListOf("default"),
        KEY_STRING_SET_DEFAULT to setOf("default"),
        KEY_PARCELABLE_DEFAULT to PARCELABLE_BUNDLE
    )
    delegate = MapDelegate(map)
  }

  @Test
  fun testGetInt() {
    assertEquals(1, delegate.getInt(KEY_INT_DEFAULT, 2))
    assertEquals(2, delegate.getInt(KEY_INT, 2))
  }

  @Test
  fun testPutInt() {
    delegate.putInt(KEY_INT, 1)
    assertEquals(1, map[KEY_INT])
  }

  @Test
  fun testGetBoolean() {
    assertTrue { delegate.getBoolean(KEY_BOOLEAN_DEFAULT, false) }
    assertFalse { delegate.getBoolean(KEY_BOOLEAN, false) }
  }

  @Test
  fun testPutBoolean() {
    delegate.putBoolean(KEY_BOOLEAN, true)
    assertEquals(true, map[KEY_BOOLEAN])
  }

  @Test
  fun testGetLong() {
    assertEquals(1, delegate.getLong(KEY_LONG_DEFAULT, 2))
    assertEquals(2, delegate.getLong(KEY_LONG, 2))
  }

  @Test
  fun testPutLong() {
    delegate.putLong(KEY_LONG, 1)
    assertEquals(1L, map[KEY_LONG])
  }

  @Test
  fun testGetString() {
    assertEquals("default", delegate.getString(KEY_STRING_DEFAULT, "val"))
    assertEquals("val", delegate.getString(KEY_STRING, "val"))
  }

  @Test
  fun testPutString() {
    delegate.putString(KEY_STRING, "val")
    assertEquals("val", map[KEY_STRING])
  }

  @Test
  fun testGetDouble() {
    assertEquals(1.0, delegate.getDouble(KEY_DOUBLE_DEFAULT, 2.0))
    assertEquals(2.0, delegate.getDouble(KEY_DOUBLE, 2.0))
  }

  @Test
  fun testPutDouble() {
    delegate.putDouble(KEY_DOUBLE, 2.0)
    assertEquals(2.0, map[KEY_DOUBLE])
  }

  @Test
  fun testGetFloat() {
    assertEquals(1f, delegate.getFloat(KEY_FLOAT_DEFAULT, 2f))
    assertEquals(2f, delegate.getFloat(KEY_FLOAT, 2f))
  }

  @Test
  fun testPutFloat() {
    delegate.putFloat(KEY_FLOAT, 2f)
    assertEquals(2f, map[KEY_FLOAT])
  }

  @Test
  fun testGetStringSet() {
    assertEquals(setOf("default"), delegate.getStringSet(KEY_STRING_SET_DEFAULT, emptySet()))
    assertEquals(emptySet(), delegate.getStringSet(KEY_STRING_SET, emptySet()))
  }

  @Test
  fun testPutStringSet() {
    delegate.putStringSet(KEY_STRING_SET, setOf("val"))
    assertEquals(setOf("val"), map[KEY_STRING_SET])
  }

  @Test
  fun testGetStringList() {
    assertEquals(arrayListOf("default"), delegate.getStringList(KEY_STRING_LIST_DEFAULT, emptyList()))
    assertEquals(emptyList(), delegate.getStringList(KEY_STRING_LIST, emptyList()))
  }

  @Test
  fun testPutStringList() {
    delegate.putStringList(KEY_STRING_LIST, arrayListOf("val"))
    assertEquals(arrayListOf("val"), map[KEY_STRING_LIST])
  }

  @Test
  fun testGetParcelable() {
    val bundle = Bundle()
    assertEquals(PARCELABLE_BUNDLE, delegate.getParcelable(KEY_PARCELABLE_DEFAULT, Bundle()))
    assertEquals(bundle, delegate.getParcelable(KEY_PARCELABLE, bundle))
  }

  @Test
  fun testPutParcelable() {
    delegate.putParcelable(KEY_PARCELABLE, PARCELABLE_BUNDLE)
    assertEquals(PARCELABLE_BUNDLE, map[KEY_PARCELABLE])
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
    assertFalse { map.containsKey(KEY_INT_DEFAULT) }
  }

  @Test
  fun testClear() {
    delegate.clear()
    assertTrue { KEY_INT_DEFAULT !in delegate }
    assertTrue { map.isEmpty() }
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
    const val KEY_DOUBLE_DEFAULT = "KEY_DOUBLE_DEFAULT"
    const val KEY_FLOAT = "KEY_FLOAT"
    const val KEY_FLOAT_DEFAULT = "KEY_FLOAT_DEFAULT"
    const val KEY_STRING_SET = "KEY_STRING_SET"
    const val KEY_STRING_SET_DEFAULT = "KEY_STRING_SET_DEFAULT"
    const val KEY_STRING_LIST = "KEY_STRING_LIST"
    const val KEY_STRING_LIST_DEFAULT = "KEY_STRING_LIST_DEFAULT"
    const val KEY_PARCELABLE = "KEY_PARCELABLE"
    const val KEY_PARCELABLE_DEFAULT = "KEY_PARCELABLE_DEFAULT"

    val PARCELABLE_BUNDLE = Bundle().apply { putInt(KEY_INT, 1) }
  }
}