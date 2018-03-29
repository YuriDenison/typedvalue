package io.denison.typedvalue.sample.preferences

import io.denison.typedvalue.common.BoolValue
import io.denison.typedvalue.common.FloatValue
import io.denison.typedvalue.sample.preferences.value.AnimationTypeValue

interface AppPreferences {
  val animationTypeValue: AnimationTypeValue
  val scaleValue: FloatValue
  val messageShownValue: BoolValue
}