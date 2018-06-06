package io.github.tturiyo.tturiyo_android.ui.customer.productlistmap

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.ObservableField
import android.support.v4.view.ViewPager
import android.view.View
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.base.debug.logd
import io.github.tturiyo.base.viewmodel.toObservable
import io.github.tturiyo.tturiyo_android.data.domain.Product
import io.github.tturiyo.tturiyo_android.data.repo.ProductRepo
import io.github.tturiyo.tturiyo_android.managers.*
import io.github.tturiyo.tturiyo_android.ui.customer.productlistmap.pager.ProductListPagerAdapter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import kotlinx.android.synthetic.main.fragment_customer_productlistmap.view.*
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView


class ProductListMapViewModel() : ViewModel() {
    private val disposables = CompositeDisposable()

    val productList: ObservableField<List<Product>> = ObservableField(emptyList())
    val selectedItem: ObservableField<Int> = ObservableField(0)
    private var inflatedView: View? = null

    init {
        Log.d()
        initData()
    }

    private fun initData() {
        Log.d()

        disposables.add(
                ProductRepo.getListAsObservable().subscribe {
                    Log.d("ProductRepo.getListAsObservable().subscribe")
                    productList.set(it)
                }
        )
    }

    fun attachView(inflatedView: View, ctx: Context) {
        Log.d()
        this.inflatedView = inflatedView
        initMapView(inflatedView.mapview)
        inflatedView.viewpager_productlist.adapter = ProductListPagerAdapter(ctx)
        inflatedView.viewpager_productlist.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                Log.d("state=$state")
                if (state == 0) {
                    selectedItem.set(inflatedView.viewpager_productlist.currentItem)
                }
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                Log.d("position=$position, positionOffset=$positionOffset, positionOffsetPixels=$positionOffsetPixels")
            }

            override fun onPageSelected(position: Int) {
                Log.d("position=$position")
            }
        })

        selectedItem.toObservable()
                .logd()
                .subscribe {
                    inflatedView.viewpager_productlist.setCurrentItem(it, true)
                }
    }

    private fun initMapView(mapView: MapView) {
        Log.d()

        mapView.focusCurrentLocationOnce()
        disposables.add(
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
        )

        disposables.add(
                mapView.getItemClickedObservable()
                        .subscribe(selectedItem::set)
        )

        //region Focus selected location
        val selectedItemObservable = selectedItem.toObservable()
        disposables.add(
                Observables.combineLatest(
                        productList.toObservable(),
                        selectedItemObservable)
                        .filter { (_, idx) ->
                            idx >= 0
                        }.filter { (productList, _) ->
                            productList.isNotEmpty()
                        }.doOnNext { (_, idx) ->
                            mapView.selectMarker(idx)
                        }
                        .map { (productList, idx) ->
                            productList[idx]
                        }.map {
                            MapPoint.mapPointWithGeoCoord(it.location.latitude, it.location.longitude)
                        }.subscribe {
                            mapView.setMapCenterPoint(it, true)
                        }
        )
        //endregion
    }

    override fun onCleared() {
        Log.d()
        inflatedView?.mapview?.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOff
        disposables.dispose()

        super.onCleared()
    }
}
