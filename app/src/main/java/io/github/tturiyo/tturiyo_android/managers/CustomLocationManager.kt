package io.github.tturiyo.tturiyo_android.managers

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.location.LocationManager
import android.support.v4.app.ActivityCompat
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.tturiyo_android.GlobalApplication
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject


object CustomLocationManager: LocationListener {
    private val locationManager: LocationManager
    private val locationSubject: BehaviorSubject<Location> = BehaviorSubject.create()

    init {
        Log.d()
        val ctx: Context = GlobalApplication.context.get()!!
        locationManager = ctx.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        requestUpdates()
    }

    @SuppressLint("MissingPermission")
    private fun requestUpdates() {
        Log.d()
        if (chkPermissions(Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION)) {
            Log.d("RequestLocationUpdates")
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0.toFloat(), this)
        }
    }

    override fun onLocationChanged(location: Location?) {
        Log.d("location=$location")
        locationSubject.onNext(location!!)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        Log.d("provider=$provider, status=$status, extras=$extras")
    }

    override fun onProviderEnabled(provider: String?) {
        Log.d("provider=$provider")
    }

    override fun onProviderDisabled(provider: String?) {
        Log.d("provider=$provider")
    }

    fun getLocationsAsObservable(): Observable<Location> {
        return locationSubject
    }

    fun init() {
        Log.d()
    }
}