package io.github.tturiyo.base.ui

import android.app.Activity
import android.content.Intent
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.ViewGroup

private val TAG = "BaseNavigator"

object BaseNavigator {
    fun gotoActivityWithNoBackstack(parent: Activity, childClazz: Class<out Activity>) {
        Log.d(TAG, "gotoActivity: ")

        gotoActivityWithBackstack(parent, childClazz)
        parent.finish()
    }

    fun gotoActivityWithBackstack(parent: Activity, childClazz: Class<out Activity>) {
        Log.d(TAG, "gotoActivity: ")

        val intent = Intent(parent, childClazz)
        ContextCompat.startActivity(parent, intent, null)
    }


    fun gotoFragmentWithActivity(parent: AppCompatActivity, childClazz: Class<out Fragment>, @IdRes containerId: Int) {
        gotoFragment(parent, childClazz, containerId, true)
    }

    fun gotoFragmentWithBackstack(parent: Fragment, childClazz: Class<out Fragment>) {
        gotoFragmentFromFragment(parent, childClazz, true)
    }

    fun gotoFragmentWithNoBackstack(parent: Fragment, childClazz: Class<out Fragment>) {
        gotoFragmentFromFragment(parent, childClazz, false)
    }

    //region private
    private fun gotoFragmentFromFragment(parent: Fragment, childClazz: Class<out Fragment>, leaveBackstack: Boolean) {
        val containerId = checkNotNull(findContainerId(parent))

        gotoFragment(parent.activity, childClazz, containerId, leaveBackstack)
    }

    private fun findContainerId(parent: Fragment): Int? {
        var containerId: Int? = null
        try {
            containerId = (parent.view!!.parent as ViewGroup).id
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

        return containerId
    }

    private fun gotoFragment(parent: FragmentActivity?, childClazz: Class<out Fragment>, @IdRes containerId: Int, leaveBackstack: Boolean) {
        Log.d(TAG, "gotoFragmentWithBackstack: ")

        val newFragment = childClazz.newInstance()

        val supportFragmentManager = parent!!.supportFragmentManager
        if (!leaveBackstack) {
            supportFragmentManager.popBackStack()
        }
        supportFragmentManager.beginTransaction().replace(containerId, newFragment).addToBackStack(null).commit()
    }
//endregion
}