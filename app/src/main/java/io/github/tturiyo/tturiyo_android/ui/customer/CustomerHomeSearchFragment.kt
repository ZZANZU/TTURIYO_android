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
import kotlinx.android.synthetic.main.fragment_customer_home_search.view.*

/**
 * Created by user on 2018-05-22.
 */
class CustomerHomeSearchFragment: Fragment(), OnMapReadyCallback {
    lateinit var mMapView: MapView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("customer home search fragment created!")
        val mView = inflater.inflate(R.layout.fragment_customer_home_search, container, false)

        mMapView = mView.customer_search_map as MapView
        mMapView.onCreate(savedInstanceState)
        mMapView.getMapAsync(this)
        mMapView.onResume()

        return mView
    }

    override fun onStart() {
        super.onStart()
        mMapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mMapView.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mMapView.onSaveInstanceState(outState)
    }

    override fun onResume() {
        super.onResume()
        mMapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMapView.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMapView.onDestroy()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if(mMapView != null) {
            mMapView.onCreate(savedInstanceState)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
//        MapsInitializer.initialize(activity)

        val anam = LatLng(37.58, 127.03)

        googleMap.addMarker(MarkerOptions().position(anam).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(anam))
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