# TypedValue
[![Build Status](https://travis-ci.org/YuriDenison/typedvalue.svg?branch=master)](https://travis-ci.org/YuriDenison/typedvalue)
![Coverage](https://img.shields.io/codecov/c/github/yuridenison/typedvalue.svg)
[![Download](https://api.bintray.com/packages/volkman/android/typedvalue/images/download.svg) ](https://bintray.com/volkman/android/typedvalue/_latestVersion)
![License](https://img.shields.io/badge/license-apache%202.0-blue.svg)

TypedValue is a tiny library project to simplify access to SharedPreferences, Bundle or any other key-value storage with it's own [`KeyValueDelegate`][key_value_delegate] 

Common values are available out of the box: 
* ['BoolValue'][bool_value]
* ['StringValue'][string_value]
* ['IntValue'][int_value]
* ['LongValue'][long_value]
* ['FloatValue'][float_value]
* ['DoubleValue'][double_value]
* ['ParcelableValue'][parcelable_value]
* ['StringListValue'][string_list_value]
* ['StringSetValue'][string_set_value]


## Demo app
![image](https://github.com/YuriDenison/typedvalue/blob/master/art/sample.gif)

The `sample` application demonstrates: 
* Convenient way to manage application preferences: ['AppPreferences'][app_preferences] and ['AppPreferencesImpl'][app_preferences_impl]
  
  Any calls (`get()`, `set()`, `asObservable()`, etc.) of each preference can be easily find via interface properties
  ``` kotlin
   interface AppPreferences {
     val animationTypeValue: AnimationTypeValue
     val scaleValue: FloatValue
     val messageShownValue: BoolValue
   }
  ```
  ``` kotlin
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
  ```


* benefits of observable SharedPreferences with uni-directional data flow of ['SettingsPresenter'][settings_presenter]
* custom ['TypedValue'][typed_value]: [AnimationTypeValue][animation_type_value]

## Installation
Add the following dependency to your `build.gradle` file:

```
dependencies {
    implementation 'io.denison:typedvalue:${LATEST_VERSION}'
}
```

## License
Please see [LICENSE](/LICENSE)


[key_value_delegate]: https://github.com/YuriDenison/typedvalue/blob/master/library/src/main/java/io/denison/typedvalue/KeyValueDelegate.kt
[typed_value]: https://github.com/YuriDenison/typedvalue/blob/master/library/src/main/java/io/denison/typedvalue/TypedValue.kt
[animation_type_value]: https://github.com/YuriDenison/typedvalue/blob/master/sample/src/main/java/io/denison/typedvalue/sample/preferences/value/AnimationTypeValue.kt
[settings_presenter]: https://github.com/YuriDenison/typedvalue/blob/master/sample/src/main/java/io/denison/typedvalue/sample/ui/settings/SettingsPresenter.kt
[app_preferences]: https://github.com/YuriDenison/typedvalue/blob/master/sample/src/main/java/io/denison/typedvalue/sample/preferences/AppPreferences.kt
[app_preferences_impl]: https://github.com/YuriDenison/typedvalue/blob/master/sample/src/main/java/io/denison/typedvalue/sample/preferences/AppPreferencesImpl.kt
[bool_value]: https://github.com/YuriDenison/typedvalue/blob/master/library/src/main/java/io/denison/typedvalue/common/BoolValue.kt
[double_value]: https://github.com/YuriDenison/typedvalue/blob/master/library/src/main/java/io/denison/typedvalue/common/DoubleValue.kt
[float_value]: https://github.com/YuriDenison/typedvalue/blob/master/library/src/main/java/io/denison/typedvalue/common/FloatValue.kt
[int_value]: https://github.com/YuriDenison/typedvalue/blob/master/library/src/main/java/io/denison/typedvalue/common/IntValue.kt
[long_value]: https://github.com/YuriDenison/typedvalue/blob/master/library/src/main/java/io/denison/typedvalue/common/LongValue.kt
[parcelable_value]: https://github.com/YuriDenison/typedvalue/blob/master/library/src/main/java/io/denison/typedvalue/common/ParcelableValue.kt
[string_list_value]: https://github.com/YuriDenison/typedvalue/blob/master/library/src/main/java/io/denison/typedvalue/common/StringListValue.kt
[string_set_value]: https://github.com/YuriDenison/typedvalue/blob/master/library/src/main/java/io/denison/typedvalue/common/StringSetValue.kt
[string_value]: https://github.com/YuriDenison/typedvalue/blob/master/library/src/main/java/io/denison/typedvalue/common/StringValue.kt