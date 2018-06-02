package io.github.tturiyo.tturiyo_android.ui.customer

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.tturiyo_android.R
import io.github.tturiyo.tturiyo_android.managers.CustomLocationManager
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_customer_home_search.view.*
import net.daum.mf.map.api.MapCircle
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapPolyline
import net.daum.mf.map.api.MapView


/**
 * Created by user on 2018-05-22.
 */
class CustomerHomeSearchFragment: Fragment() {
    companion object {
        fun newInstance(): CustomerHomeSearchFragment {
            return CustomerHomeSearchFragment()
        }
    }

    val disposables = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("customer home search fragment created!")
        val mView = inflater.inflate(R.layout.fragment_customer_home_search, container, false)

        val mapView = MapView(activity)

        disposables.add(
                CustomLocationManager.getLocationsAsObservable()
                        .take(1)
                        .subscribe {
                            Log.d("First Location Event Location=$it")
                            mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(it.latitude, it.longitude), true)
                            mapView.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOnWithMarkerHeadingWithoutMapMoving
//                            mapView.setCurrentLocationRadius(100)
//                            mapView.setCurrentLocationRadiusFillColor(Color.argb(77, 255, 255, 0))
//                            mapView.setCurrentLocationRadiusStrokeColor(Color.argb(77, 255, 165, 0))
                            mapView.setShowCurrentLocationMarker(true)
                            mView.map_frame.addView(mapView)
                        }
        )

        return mView
    }
}