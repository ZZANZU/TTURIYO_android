package io.github.tturiyo.tturiyo_android.ui.seller.productlist

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.ObservableField
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jakewharton.rxbinding2.view.RxView
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.tturiyo_android.GlobalApplication
import io.github.tturiyo.tturiyo_android.data.domain.Product
import io.github.tturiyo.tturiyo_android.data.file.getUuid
import io.github.tturiyo.tturiyo_android.data.repo.ProductRepo
import io.github.tturiyo.tturiyo_android.ui.adapter.ProductAdapter
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_seller_productlist.view.*


class SellerProductListViewModel(private val navigator: SellerProductListNavigator) : ViewModel() {
    private val disposables = CompositeDisposable()

    val productList: ObservableField<List<Product>> = ObservableField(emptyList())

    init {
        Log.d()
        initData()
    }

    private fun initData() {
        Log.d()

        ProductRepo.getListWithUidAsObservable(
                GlobalApplication.context.get()!!.getUuid())
                .subscribe {
                    Log.d("ProductRepo.getListAsObservable().subscribe")
                    productList.set(it)
                }
    }

    fun attachView(inflatedView: View, ctx: Context) {
        Log.d()

        disposables.add(
                RxView.clicks(inflatedView.btn_add)
                        .subscribe {
                            navigator.gotoNewProductFragment()
                        }
        )

        inflatedView.recyclerview_sellerproductlist.adapter = ProductAdapter()
        inflatedView.recyclerview_sellerproductlist.layoutManager = LinearLayoutManager(ctx)
    }

    override fun onCleared() {
        Log.d()
        disposables.dispose()

        super.onCleared()
    }
}