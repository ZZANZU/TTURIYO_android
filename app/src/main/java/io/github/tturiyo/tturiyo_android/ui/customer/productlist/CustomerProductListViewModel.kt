package io.github.tturiyo.tturiyo_android.ui.customer.productlist

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.ObservableField
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.tturiyo_android.data.domain.Product
import io.github.tturiyo.tturiyo_android.data.repo.ProductRepo
import io.github.tturiyo.tturiyo_android.ui.adapter.CustomerProductAdapter
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_customer_productlist.view.*


class CustomerProductListViewModel(private val navigator: CustomerProductListNavigator) : ViewModel() {
    private val disposables = CompositeDisposable()

    val productList: ObservableField<List<Product>> = ObservableField(emptyList())

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

        inflatedView.recyclerview_productList.adapter = CustomerProductAdapter(navigator = navigator)
        inflatedView.recyclerview_productList.layoutManager = LinearLayoutManager(ctx)
    }

    override fun onCleared() {
        Log.d()
        disposables.dispose()

        super.onCleared()
    }
}