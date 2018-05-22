package io.github.tturiyo.tturiyo_android.ui.customer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.tturiyo.tturiyo_android.R

/**
 * Created by user on 2018-05-22.
 */
class CustomerHomeSearchFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mView = inflater.inflate(R.layout.fragment_customer_home_search, container, false)

        return mView
    }
}