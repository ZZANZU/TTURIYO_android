package io.github.tturiyo.tturiyo_android.ui.seller.map

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.ObservableField
import android.view.View
import com.jakewharton.rxbinding2.view.RxView
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.base.debug.logd
import io.github.tturiyo.base.ui.BaseNavigator
import io.github.tturiyo.base.viewmodel.toObservable
import io.github.tturiyo.tturiyo_android.GlobalApplication
import io.github.tturiyo.tturiyo_android.data.domain.Location
import io.github.tturiyo.tturiyo_android.data.domain.Product
import io.github.tturiyo.tturiyo_android.data.domain.toLocation
import io.github.tturiyo.tturiyo_android.data.file.getUuid
import io.github.tturiyo.tturiyo_android.data.repo.ProductRepo
import io.github.tturiyo.tturiyo_android.managers.*
import io.github.tturiyo.tturiyo_android.ui.seller.ProductData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.fragment_seller_map.view.*
import kotlinx.android.synthetic.main.item_seller_product_expanded.view.*
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView


class MapViewModel(private val navigator: MapNavigator) : ViewModel() {
    private val disposables = CompositeDisposable()

    private var inflatedView: View? = null
    private val selectedLocationBehaviorSubject = BehaviorSubject.create<MapPoint>()

    init {
        Log.d()
    }

    fun attachView(inflatedView: View) {
        Log.d()
        this.inflatedView = inflatedView
        initMapView(inflatedView.mapview)

        disposables.add(
                RxView.clicks(inflatedView.btn_back)
                        .subscribe {
                            Log.d("RxView.clicks(inflatedView.btn_back)")
                            navigator.backPressed()
                        }
        )

        disposables.add(
                RxView.clicks(inflatedView.btn_confirm)
                        .subscribe {
                            ProductData.data.location = selectedLocationBehaviorSubject.value!!.toLocation()
                            val item = ProductData.getAndClear()
                            item.uid = GlobalApplication.context.get()!!.getUuid()
                            item.currentStock = item.numberOfStock
                            ProductRepo.insert(item = item,
                                    onSuccess = { navigator.gotoSellerProductListFragmentWithClearingBackstack() })
                        }
        )


        disposables.add(
                selectedLocationBehaviorSubject
                        .logd()
                        .subscribe {
                            inflatedView.mapview.removeAllPOIItems()
                            val marker = MapPOIItem()
                            marker.itemName = ""
                            marker.tag = 0
                            marker.mapPoint = it
                            marker.markerType = MapPOIItem.MarkerType.BluePin
                            marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin
                            marker.isShowDisclosureButtonOnCalloutBalloon = false
                            marker.isShowCalloutBalloonOnTouch = false

                            inflatedView.mapview.selectPOIItem(marker, false)
                            inflatedView.mapview.addPOIItem(marker)
                        }
        )

        disposables.add(
                selectedLocationBehaviorSubject
                        .subscribe {
                            inflatedView.btn_back.visibility = View.VISIBLE
                            inflatedView.btn_confirm.visibility = View.VISIBLE
                        }
        )
    }

    private fun initMapView(mapView: MapView) {
        Log.d()

        mapView.focusCurrentLocationOnce()
        disposables.add(
                mapView.getMapClickedObservable()
                        .subscribe(selectedLocationBehaviorSubject::onNext)
        )
    }

    override fun onCleared() {
        Log.d()
        inflatedView?.mapview?.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOff
        disposables.dispose()

        super.onCleared()
    }
}
