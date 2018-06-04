package io.github.tturiyo.tturiyo_android

import android.app.Application
import android.content.Context
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.tturiyo_android.data.file.saveOnInternalStorage
import io.github.tturiyo.tturiyo_android.data.file.hasUuidExists
import java.lang.ref.WeakReference
import java.util.*


class GlobalApplication : Application() {
    companion object {
        var context: WeakReference<Context> = WeakReference<Context>(null)
    }

    override fun onCreate() {
        super.onCreate()
        Log.d()
        context = WeakReference(this)
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
            saveOnInternalStorage(uniqueID)
        }
    }

    override fun onTerminate() {
        context = WeakReference<Context>(null)
        super.onTerminate()
    }
}