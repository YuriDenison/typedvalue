package io.denison.typedvalue.sample.preferences

import android.app.Application
import android.preference.PreferenceManager
import io.denison.typedvalue.common.BoolValue
import io.denison.typedvalue.common.FloatValue
import io.denison.typedvalue.delegate.PreferenceDelegate
import io.denison.typedvalue.sample.model.AnimationType
import io.denison.typedvalue.sample.preferences.value.AnimationTypeValue

class AppPreferencesImpl(app: Application) : AppPreferences {
  private val delegate = PreferenceDelegate(PreferenceManager.getDefaultSharedPreferences(app))

  override val animationTypeValue: AnimationTypeValue by lazy { AnimationTypeValue(delegate, KEY_ANIMATION_TYPE, AnimationType.CONFETTI) }
  override val scaleValue: FloatValue by lazy { FloatValue(delegate, KEY_SIZE, 0.8f) }
  override val messageShownValue: BoolValue by lazy { BoolValue(delegate, KEY_MESSAGE_SHOWN) }

  private companion object {
    const val KEY_ANIMATION_TYPE = ".key_animation_type"
    const val KEY_SIZE = ".key_size"
    const val KEY_MESSAGE_SHOWN = ".key_message_shown"
  }
}