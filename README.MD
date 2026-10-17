for my project

# add submodule
```powershell
git submodule add https://github.com/lanlacope/android-rewheel.git libs/android-rewheel
git submodule update --init --recursive
```

# updare latest
```powershell
git submodule update --remote
```

# in project
`settings.gradle.kts`
```kotlin
implementation(libs.lanlacope.rewheel)
```
`libs.versions.toml`
```toml
lanlacope-rewheel = { group = "io.github.lanlacope.android-rewheel", name = "army-knife" }
```
`build.gradle.kts`
```kotlin
includeBuild("libs/android-rewheel")
```