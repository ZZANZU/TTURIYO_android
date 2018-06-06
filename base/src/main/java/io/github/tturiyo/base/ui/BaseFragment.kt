package io.github.tturiyo.base.ui

import android.support.v4.app.Fragment


open class BaseFragment : Fragment() {
    open fun onBackPressed(): Boolean {
        return false
    }
}
