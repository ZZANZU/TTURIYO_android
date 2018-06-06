package io.github.tturiyo.tturiyo_android.managers

import android.Manifest
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.base.debug.assertDebug
import io.reactivex.Single
import io.reactivex.subjects.SingleSubject
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

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
