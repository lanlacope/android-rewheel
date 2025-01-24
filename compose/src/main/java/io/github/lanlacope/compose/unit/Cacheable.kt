package io.github.lanlacope.compose.unit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import java.io.File


@Composable
fun <T : Any>rememberCacheable(
    key: String,
    init: () -> T
): T {
    val lifecycleOwner = LocalLifecycleOwner.current
    val dataStore = rememberCachePreferencesDataStore()

    val savable = rememberSaveable(key = key) {
        runBlocking {
            dataStore.getCache(key, init())
        }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_STOP) {
                runBlocking {
                    dataStore.setCache(key, savable)
                }
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    return savable
}

private suspend fun <T> DataStore<Preferences>.getCache(
    key: String,
    defaultValue: T
): T {
    val preferencesKey = createPreferencesKey(defaultValue, key)
    return if (defaultValue is MutableState<*>) {
        @Suppress("UNCHECKED_CAST")
        mutableStateOf(this.data.map { preferences ->
            preferences[preferencesKey]
        }.first() ?: defaultValue.value) as T
    } else {
        this.data.map { preferences ->
            preferences[preferencesKey]
        }.first() ?: defaultValue
    }
}

private suspend fun <T> DataStore<Preferences>.setCache(
    key: String,
    value: T
) {
    val preferencesKey = createPreferencesKey(value, key)
    if (value is MutableState<*>) {
        this.edit { preferences ->
            @Suppress("UNCHECKED_CAST")
            preferences[preferencesKey] = value.value as T
        }
    } else {
        this.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }
}

@Suppress("UNCHECKED_CAST")
private fun <T> createPreferencesKey(defaultValue: T, key: String): Preferences.Key<T> {
    return when (defaultValue) {
        is Int -> intPreferencesKey(key) as Preferences.Key<T>
        is Double -> doublePreferencesKey(key) as Preferences.Key<T>
        is String -> stringPreferencesKey(key) as Preferences.Key<T>
        is Boolean -> booleanPreferencesKey(key) as Preferences.Key<T>
        is Float -> floatPreferencesKey(key) as Preferences.Key<T>
        is Long -> longPreferencesKey(key) as Preferences.Key<T>
        is MutableState<*> -> {
            when (defaultValue.value) {
                is Int -> intPreferencesKey(key) as Preferences.Key<T>
                is Double -> doublePreferencesKey(key) as Preferences.Key<T>
                is String -> stringPreferencesKey(key) as Preferences.Key<T>
                is Boolean -> booleanPreferencesKey(key) as Preferences.Key<T>
                is Float -> floatPreferencesKey(key) as Preferences.Key<T>
                is Long -> longPreferencesKey(key) as Preferences.Key<T>
                else -> throw IllegalArgumentException("Unsupported type: ${defaultValue.value!!::class.java}")
            }
        }
        else -> throw IllegalArgumentException("Unsupported type: ${defaultValue!!::class.java}")
    }
}

private lateinit var cachePreferencesDataStore: DataStore<Preferences>

@Composable
private fun rememberCachePreferencesDataStore(): DataStore<Preferences> {
    val appContext = LocalContext.current.applicationContext
    if (!::cachePreferencesDataStore.isInitialized) {
        cachePreferencesDataStore = PreferenceDataStoreFactory.create(
            produceFile = {
                File(
                    appContext.cacheDir,
                    "datastore/remember_cacheable.preferences_pb"
                )
            }
        )
    }
    return remember { cachePreferencesDataStore }
}