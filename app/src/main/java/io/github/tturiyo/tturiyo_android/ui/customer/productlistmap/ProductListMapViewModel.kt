package io.github.tturiyo.tturiyo_android.ui.customer.productlistmap

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.ObservableField
import android.support.design.widget.TabLayout
import android.view.View
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.base.viewmodel.toObservable
import io.github.tturiyo.tturiyo_android.data.domain.Product
import io.github.tturiyo.tturiyo_android.data.repo.ProductRepo
import io.github.tturiyo.tturiyo_android.managers.clear
import io.github.tturiyo.tturiyo_android.managers.drawMarkers
import io.github.tturiyo.tturiyo_android.managers.focusCurrentLocationOnce
import io.github.tturiyo.tturiyo_android.ui.customer.productlistmap.pager.ProductListPagerAdapter
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_customer_productlistmap.view.*
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView


class ProductListMapViewModel() : ViewModel() {
    private val disposables = CompositeDisposable()

    val productList: ObservableField<List<Product>> = ObservableField(emptyList())
    private var inflatedView: View? = null

    init {
        Log.d()
        initData()
    }

    private fun initData() {
        Log.d()

        ProductRepo.getListAsObservable().subscribe {
            Log.d("ProductRepo.getListAsObservable().subscribe")
            productList.set(it)
        }
    }

    fun attachView(inflatedView: View, ctx: Context) {
        Log.d()
        this.inflatedView = inflatedView.mapview
        initMapView(inflatedView.mapview)
        inflatedView.viewpager_productlist.adapter = ProductListPagerAdapter(ctx)
    }

    private fun initMapView(mapView: MapView) {
        Log.d()

        mapView.focusCurrentLocationOnce()
        productList.toObservable()
                .map {
                    it.map {
                        it.location
                    }.map {
                        MapPoint.mapPointWithGeoCoord(it.latitude, it.longitude)
                    }
                }.subscribe {
                    Log.d("drawMarker / productList.toObservable().subscribe it=$it")
                    mapView.clear()
                    mapView.drawMarkers(it)
                }
    }

    override fun onCleared() {
        Log.d()
        inflatedView?.mapview?.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOff
        disposables.dispose()

        super.onCleared()
    }
}