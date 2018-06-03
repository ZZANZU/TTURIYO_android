package io.github.tturiyo.tturiyo_android.ui.customer

import android.Manifest
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.tturiyo_android.R
import io.github.tturiyo.tturiyo_android.managers.chkPermissions
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_customer_home_search.view.*
import net.daum.mf.map.api.MapPoint
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

    private lateinit var mapView: MapView
    private val disposables = CompositeDisposable()
    private lateinit var inflatedView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("customer home search fragment created!")
        inflatedView = inflater.inflate(R.layout.fragment_customer_home_search, container, false)

        mapView = MapView(activity)
        if (chkPermissions(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION)) {
            Log.d("RequestLocationUpdates")
            mapView.setCurrentLocationEventListener(object : MapView.CurrentLocationEventListener {
                override fun onCurrentLocationUpdateFailed(p0: MapView?) {
                    Log.d()
                }

                override fun onCurrentLocationUpdate(p0: MapView?, p1: MapPoint?, p2: Float) {
                    Log.d()
                    Log.d("latitude=${p1!!.mapPointGeoCoord.latitude}, longitude=${p1.mapPointGeoCoord.longitude}")
                    if (mapView.currentLocationTrackingMode == MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading) {
                        Log.d("mapView.currentLocationTrackingMode == MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading")
                        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(p1.mapPointGeoCoord.latitude, p1.mapPointGeoCoord.longitude), false)
                        mapView.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeadingWithoutMapMoving
                    }
                }

                override fun onCurrentLocationUpdateCancelled(p0: MapView?) {
                    Log.d()
                }

                override fun onCurrentLocationDeviceHeadingUpdate(p0: MapView?, p1: Float) {
                    Log.d()
                }

            })

            mapView.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
            inflatedView.map_frame.addView(mapView)
        } else {
            assert(false, { "권한이 없습니다." })
        }

        return inflatedView
    }

    override fun onResume() {
        super.onResume()
        Log.d()
    }

    override fun onPause() {
        super.onPause()
        Log.d()
    }

    override fun onDestroy() {
        Log.d()
        super.onDestroy()
    }

    override fun onDestroyView() {
        Log.d()
        mapView.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOff
        inflatedView.map_frame.removeView(mapView)
        disposables.dispose()

        super.onDestroyView()
    }
}