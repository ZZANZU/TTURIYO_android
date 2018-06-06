package io.github.tturiyo.base.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.base.debug.assertDebug

open class BaseFragmentActivity : AppCompatActivity() {
    protected var contentFrameId = 0
    protected var initialFragmentClazz: Class<out Fragment>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        assertDebug(contentFrameId != 0)
        assertDebug(initialFragmentClazz != null)

        val savedFragment = supportFragmentManager.findFragmentById(contentFrameId)
        if (savedFragment == null) {
            initFragment()
        }
    }

    private fun initFragment() {
        BaseNavigator.gotoFragmentWithActivity(this, initialFragmentClazz!!, contentFrameId)
    }

    open override fun onBackPressed() {
        Log.d()

        assertDebug(contentFrameId != 0)
        val fragment = supportFragmentManager.findFragmentById(contentFrameId)

        var consumed = false
        if (fragment is BaseFragment) {
            consumed = fragment.onBackPressed()
        }

        if (consumed) {
            return
        }

        if (supportFragmentManager.backStackEntryCount <= 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}
