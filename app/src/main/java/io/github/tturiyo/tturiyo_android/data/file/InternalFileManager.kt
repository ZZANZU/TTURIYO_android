package io.github.tturiyo.tturiyo_android.data.file

import android.content.Context
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.base.debug.assertDebug
import java.io.File
import java.util.*


const val UUIDFILENAME = "uuid"

fun Context.hasUuidExists(): Boolean {
    return File(filesDir, UUIDFILENAME).exists()
}

fun Context.saveOnInternalStorage(data: String) {
    val fos = openFileOutput(UUIDFILENAME, Context.MODE_PRIVATE)
    fos.write(data.toByteArray())
    fos.close()
}

fun Context.getUuid(): String {
    val fis = openFileInput(UUIDFILENAME)
    val scanner = Scanner(fis)
    val uuid = scanner.nextLine()
    Log.d("uuid = $uuid")
    fis.close()

    assertDebug("" != uuid)
    assertDebug(uuid != null)

    return uuid
}
