package io.github.tturiyo.tturiyo_android.ui.seller.map

import android.Manifest
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.base.debug.extLogd
import io.github.tturiyo.tturiyo_android.R
import io.github.tturiyo.tturiyo_android.managers.chkPermissions
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.fragment_seller_map.view.*
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class MapFragment : Fragment(), MapView.MapViewEventListener, MapView.CurrentLocationEventListener {
    companion object {
        fun newInstance(): MapFragment {
            Log.d()
            return MapFragment()
        }
    }

    private val disposables = CompositeDisposable()
    private lateinit var inflatedView: View
    private val selectedLocationBehaviorSubject: BehaviorSubject<MapPoint> = BehaviorSubject.create()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d()
        inflatedView = inflater.inflate(R.layout.fragment_seller_map, container, false)
        val mapView = inflatedView.mapview
        initMapWithCurrentLocation(mapView)
        initMapViewEventListener(mapView)

        return inflatedView
    }

    private fun initMapWithCurrentLocation(mv: MapView) {
        if (chkPermissions(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION)) {
            Log.d("RequestLocationUpdates")
            mv.setCurrentLocationEventListener(this)
            mv.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
        }
    }

    private fun initMapViewEventListener(mv: MapView) {
        Log.d()
        disposables.add(
                selectedLocationBehaviorSubject
                        .extLogd()
                        .subscribe {
                            mv.removeAllPOIItems()
                            val marker = MapPOIItem()
                            marker.itemName = ""
                            marker.tag = 0
                            marker.mapPoint = it
                            marker.markerType = MapPOIItem.MarkerType.BluePin
                            marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin
                            marker.isShowDisclosureButtonOnCalloutBalloon = false
                            marker.isShowCalloutBalloonOnTouch = false

                            mv.selectPOIItem(marker, false)
                            mv.addPOIItem(marker)
                        }
        )

        mv.setMapViewEventListener(this)
    }

    override fun onDestroy() {
        Log.d()
        disposables.dispose()
        super.onDestroy()
    }

    // region MapViewEventListener
    override fun onMapViewDoubleTapped(p0: MapView, p1: MapPoint?) {
        Log.d()
    }

    override fun onMapViewInitialized(mapView: MapView) {
        Log.d()
//                mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(ANAM_LATITUDE, ANAM_LONGITUDE), 9, true)
    }

    override fun onMapViewDragStarted(p0: MapView, p1: MapPoint?) {
        Log.d()
    }

    override fun onMapViewMoveFinished(p0: MapView, p1: MapPoint?) {
        Log.d()
    }

    override fun onMapViewCenterPointMoved(p0: MapView, p1: MapPoint?) {
        Log.d()
    }

    override fun onMapViewDragEnded(p0: MapView, p1: MapPoint?) {
        Log.d()
    }

    override fun onMapViewSingleTapped(p0: MapView, p1: MapPoint?) {
        Log.d()
        selectedLocationBehaviorSubject.onNext(p1!!)
    }

    override fun onMapViewZoomLevelChanged(p0: MapView, p1: Int) {
        Log.d()
    }

    override fun onMapViewLongPressed(p0: MapView, p1: MapPoint?) {
        Log.d()
        selectedLocationBehaviorSubject.onNext(p1!!)
    }
    // endregion

    // region CurrentLocationEventListener
    override fun onCurrentLocationUpdateFailed(p0: MapView?) {
        Log.d()
    }

    override fun onCurrentLocationUpdate(mv: MapView, p1: MapPoint?, p2: Float) {
        Log.d()
        Log.d("latitude=${p1!!.mapPointGeoCoord.latitude}, longitude=${p1.mapPointGeoCoord.longitude}")
        if (mv.currentLocationTrackingMode == MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading) {
            Log.d("mapView.currentLocationTrackingMode == MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading")
            mv.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(p1.mapPointGeoCoord.latitude, p1.mapPointGeoCoord.longitude), false)
            mv.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeadingWithoutMapMoving
        }
    }

    override fun onCurrentLocationUpdateCancelled(p0: MapView?) {
        Log.d()
    }

    override fun onCurrentLocationDeviceHeadingUpdate(p0: MapView?, p1: Float) {
        Log.d()
    }
    // endregion
}
