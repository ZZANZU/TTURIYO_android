package io.github.tturiyo.base.keys

import android.content.Context
import android.content.pm.PackageManager
import android.util.Base64
import io.github.tturiyo.base.debug.Log
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

fun Context.getAppkey(): String {
    try {
        val info = packageManager.getPackageInfo("io.github.tturiyo.tturiyo_android", PackageManager.GET_SIGNATURES)
        for (signature in info.signatures) {
            val md = MessageDigest.getInstance("SHA")
            md.update(signature.toByteArray())
            return Base64.encodeToString(md.digest(), Base64.DEFAULT)
        }
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    }

    return ""
}
