for my project

```kotlin
// settings.gradle.kts
maven {     
    url = uri("https://maven.pkg.github.com/lanlacope/android-rewheel")
    credentials {
        username = "lanlacope"
        password = "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"
    }
}
```

```toml
# libs.versions.tomp
[versions]
lanlacope = "1.0.0"

[libraries]
lanlacope-rewheel-basic = { group = "io.github.lanlacope.android-rewheel", name = "basic", version.ref = "lanlacope"}
```

```kotlin
// build.gradle.kts
dependencies { 
    implementation(libs.lanlacope.rewheel.basic)
}
```