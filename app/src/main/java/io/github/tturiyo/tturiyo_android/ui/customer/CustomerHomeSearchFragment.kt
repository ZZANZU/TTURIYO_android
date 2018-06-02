package io.github.tturiyo.tturiyo_android.ui.customer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.tturiyo_android.R
import kotlinx.android.synthetic.main.fragment_customer_home_search.view.*

/**
 * Created by user on 2018-05-22.
 */
class CustomerHomeSearchFragment: Fragment() {
    companion object {
        fun newInstance(): CustomerHomeSearchFragment {
            return CustomerHomeSearchFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("customer home search fragment created!")
        val mView = inflater.inflate(R.layout.fragment_customer_home_search, container, false)

//        mMapView = mView.customer_search_map as MapView
//        mMapView.onCreate(savedInstanceState)
//        mMapView.getMapAsync(this)
//        mMapView.onResume()

        return mView
    }
}