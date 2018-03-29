package io.denison.typedvalue.sample.preferences.value

import io.denison.typedvalue.KeyValueDelegate
import io.denison.typedvalue.TypedValue
import io.denison.typedvalue.sample.model.AnimationType

class AnimationTypeValue(delegate: KeyValueDelegate, key: String, defaultValue: AnimationType) : TypedValue<AnimationType>(delegate, key, defaultValue) {
  override fun get(): AnimationType = AnimationType.valueOf(delegate.getString(key, defaultValue.name))
  override fun set(value: AnimationType) = delegate.putString(key, value.name)
}