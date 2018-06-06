package io.github.tturiyo.tturiyo_android.managers

import android.Manifest
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.base.debug.assertDebug
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.SingleSubject
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

fun MapView.clear() {
    Log.d()
    this.removeAllPOIItems()
}

fun MapView.drawMarkers(mapPoints: List<MapPoint>) {
    Log.d()
    mapPoints.forEachIndexed { index, mapPoint ->
        val marker = MapPOIItem()
        marker.itemName = ""
        marker.tag = index
        marker.mapPoint = mapPoint
        marker.markerType = MapPOIItem.MarkerType.BluePin
        marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin
        marker.isShowDisclosureButtonOnCalloutBalloon = false
        marker.isShowCalloutBalloonOnTouch = false

        this.addPOIItem(marker)
    }
}

fun MapView.getItemClickedObservable(): BehaviorSubject<Int> {
    val notifier = BehaviorSubject.create<Int>()
    Log.d()

    poiItemEventListener.notifier = notifier
    this.setPOIItemEventListener(poiItemEventListener)

    return notifier
}

object poiItemEventListener: MapView.POIItemEventListener {
    var notifier: BehaviorSubject<Int>? = null

    override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {
        Log.d()
    }

    override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?, p2: MapPOIItem.CalloutBalloonButtonType?) {
        Log.d()
    }

    override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {
        Log.d()
    }

    override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem) {
        Log.d("poiitem=$p1")
        notifier?.let {
            it.onNext(p1.tag)
        }
    }
}

object eventListener: MapView.MapViewEventListener {
    var notifier: BehaviorSubject<MapPoint>? = null

    override fun onMapViewDoubleTapped(p0: MapView?, p1: MapPoint) {
        Log.d()
        notifier?.onNext(p1)
    }

    override fun onMapViewInitialized(p0: MapView?) {
        Log.d()
    }

    override fun onMapViewDragStarted(p0: MapView?, p1: MapPoint?) {
        Log.d()
    }

    override fun onMapViewMoveFinished(p0: MapView?, p1: MapPoint?) {
        Log.d()
    }

    override fun onMapViewCenterPointMoved(p0: MapView?, p1: MapPoint?) {
        Log.d()
    }

    override fun onMapViewDragEnded(p0: MapView?, p1: MapPoint?) {
        Log.d()
    }

    override fun onMapViewSingleTapped(p0: MapView?, p1: MapPoint) {
        Log.d()
        notifier?.onNext(p1)
    }

    override fun onMapViewZoomLevelChanged(p0: MapView?, p1: Int) {
        Log.d()
    }

    override fun onMapViewLongPressed(p0: MapView?, p1: MapPoint?) {
        Log.d()
    }
}

fun MapView.getMapClickedObservable(): BehaviorSubject<MapPoint> {
    val notifier = BehaviorSubject.create<MapPoint>()
    Log.d()

    eventListener.notifier = notifier
    this.setMapViewEventListener(eventListener)

    return notifier
}

fun MapView.selectMarker(idx: Int) {
    Log.d("idx=$idx")
    val marker = this.findPOIItemByTag(idx)
    if (marker != null) {
        this.selectPOIItem(marker, true)
    }
}

fun MapView.focusCurrentLocationOnce() {
    Log.d()
    if (chkPermissions(Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
        Log.d("RequestLocationUpdates")

        this.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
        this.getCurrentLocationOnce().subscribe { mp: MapPoint?, t: Throwable? ->
            Log.d("mapView.getCurrentLocationOnce().subscribe")
            this.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeadingWithoutMapMoving
            mp?.let {
                this.setMapCenterPoint(it, false)
            }

            Log.d("mp=$mp, t=$t")
            if (t != null) {
                Log.d("if (t != null)")
            }
        }
    } else {
        assertDebug(false, "권한이 없습니다.")
    }
}

private fun MapView.getCurrentLocationOnce(): Single<MapPoint> {
    Log.d()
    val notifier = SingleSubject.create<MapPoint>()

    this.setCurrentLocationEventListener(object : MapView.CurrentLocationEventListener {
        override fun onCurrentLocationUpdateFailed(p0: MapView?) {
            Log.d()
        }

        override fun onCurrentLocationUpdate(mv: MapView, mp: MapPoint, p2: Float) {
            Log.d()
            Log.d("latitude=${mp.mapPointGeoCoord.latitude}, longitude=${mp.mapPointGeoCoord.longitude}")
            notifier.onSuccess(mp)
            this@getCurrentLocationOnce.setCurrentLocationEventListener(null)
        }

        override fun onCurrentLocationUpdateCancelled(mv: MapView?) {
            Log.d()
        }

        override fun onCurrentLocationDeviceHeadingUpdate(p0: MapView?, p1: Float) {
            Log.d()
        }
    })

    return notifier
}
