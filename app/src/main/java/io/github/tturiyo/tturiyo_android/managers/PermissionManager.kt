package io.github.tturiyo.tturiyo_android.managers

import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import io.github.tturiyo.tturiyo_android.GlobalApplication


private fun Context.chkPermissions(vararg permissions: String): Boolean {
    for (eachPermission in permissions) {
        if (ActivityCompat.checkSelfPermission(this, eachPermission) != PackageManager.PERMISSION_GRANTED) {
            return false
        }
    }

    return true
}

fun chkPermissions(vararg permissions: String): Boolean {
    val ctx = GlobalApplication.context.get()!!
    return ctx.chkPermissions(*permissions)
}