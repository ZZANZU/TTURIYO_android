package io.github.tturiyo.tturiyo_android

import android.app.Application
import android.content.Context
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.base.keys.getAppkey
import io.github.tturiyo.tturiyo_android.data.file.extSaveOnInternalStorage
import io.github.tturiyo.tturiyo_android.data.file.hasUuidExists
import java.util.*


class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d()
//        Log.d("Appkey: ${this.getAppkey()}")

        initUuid()
    }

    private fun initUuid() {
        Log.d()

        if (hasUuidExists()) {
            Log.d("Uuid exists")
        } else {
            Log.d("Uuid not exists")
            val uniqueID = UUID.randomUUID().toString()
            Log.d("Generated uniqueID = $uniqueID")
            extSaveOnInternalStorage(uniqueID)
        }
    }
}