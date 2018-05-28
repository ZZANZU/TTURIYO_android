package io.github.tturiyo.tturiyo_android.ui.customer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.tturiyo_android.R

/**
 * Created by user on 2018-05-22.
 */
class CustomerHomeSearchFragment: Fragment(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("customer home search fragment created!")
        val mView = inflater.inflate(R.layout.fragment_customer_home_search, container, false)

        val mapFragment = mView.findViewById(R.id.customer_search_map) as MapView
        mapFragment.getMapAsync(this)

        return mView
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val anam = LatLng(37.58, 127.03)

        mMap.addMarker(MarkerOptions().position(anam).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(anam))
    }

    companion object {
        private val ARG_SECTION_NUMBER = "section_number"

        fun newInstance(sectionNumber: Int): CustomerHomeSearchFragment {
            val fragment = CustomerHomeSearchFragment()
//            val args = Bundle()
//            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
//            fragment.arguments = args
            return fragment
        }
    }
}