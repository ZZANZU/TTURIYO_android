package io.github.tturiyo.base.ui

import android.support.v4.app.Fragment


class BaseFragment : Fragment() {
    fun onBackPressed(): Boolean {
        return false
    }
}
