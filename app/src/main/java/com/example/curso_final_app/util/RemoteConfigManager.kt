package com.example.curso_final_app.util

import android.util.Log
import com.example.curso_final_app.R
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings

object RemoteConfigManager {
    private  val TAG = RemoteConfigManager::class.java.simpleName
    private val remoteConfig: FirebaseRemoteConfig by lazy {
        FirebaseRemoteConfig.getInstance().apply {
            val configSettings = FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(0)
                .build()
            setConfigSettingsAsync(configSettings)
            setDefaultsAsync(R.xml.remote_config_defaults)
        }
    }
    init{
        Log.d(TAG,"En el init de RemoteConfigManager")
    }
    fun getString(key: String): String = remoteConfig.getString(key)
    fun getBoolean(key: String): Boolean = remoteConfig.getBoolean(key)

    fun fetchAndActivate(onComplete: (Boolean) -> Unit) {
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Fetch and activate succeeded")
                    logAllValues()
                    onComplete(true)
                } else {
                    Log.e(TAG, "Fetch failed", task.exception)
                    onComplete(false)
                }
            }
    }
    fun logAllValues(){
       val label = getString("label")
        Log.d(TAG,"Label: $label")
        val label_carmen = getString("label_carmen")
        Log.d(TAG,"Label: $label_carmen")
        /*
        val condicion = getBoolean("condicion")
        Log.d(TAG,"Condicion: $condicion")
        */
    }
}