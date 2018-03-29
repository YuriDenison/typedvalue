# TypedValue
[![Build Status](https://travis-ci.org/YuriDenison/typedvalue.svg?branch=master)](https://travis-ci.org/YuriDenison/typedvalue)
![Coverage](https://img.shields.io/codecov/c/github/yuridenison/typedvalue.svg)
[![Download](https://api.bintray.com/packages/volkman/android/typedvalue/images/download.svg) ](https://bintray.com/volkman/android/typedvalue/_latestVersion)
![License](https://img.shields.io/badge/license-apache%202.0-blue.svg)

TypedValue is a tiny library project to simplify access to SharedPreferences, Bundle or any other key-value storage with it's own [`KeyValueDelegate`][key_value_delegate] 

Common 


## Demo app
The `sample` application demonstrates: 
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
[animation_type_value]: https://github.com/YuriDenison/typedvalue/blob/master/sample/src/main/java/io/denison/typedvalue/preferences/value/AnimationTypeValue.kt
[settings_presenter]: https://github.com/YuriDenison/typedvalue/master/sample/src/main/java/io/denison/typedvalue/sample/ui/settings/SettingsPresenter.kt