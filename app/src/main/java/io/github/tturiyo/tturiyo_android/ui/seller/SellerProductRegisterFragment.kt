package io.github.tturiyo.tturiyo_android.ui.seller

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.base.ui.BaseNavigator
import io.github.tturiyo.tturiyo_android.R
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.github.tturiyo.tturiyo_android.ui.seller.map.MapFragment
import kotlinx.android.synthetic.main.activity_seller.*
import kotlinx.android.synthetic.main.fragment_seller_register.view.*


class SellerProductRegisterFragment: Fragment() {
    companion object {
        fun newInstance(): SellerProductRegisterFragment {
            Log.d()
            return SellerProductRegisterFragment()
        }
    }

    val disposables = CompositeDisposable()
    private lateinit var inflatedView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d()

        inflatedView = inflater.inflate(R.layout.fragment_seller_register, container, false)
        initView()

        return inflatedView
    }

    private fun initView() {
        Log.d()

        disposables.addAll(
                RxTextView.afterTextChangeEvents(inflatedView.et_companyname)
                        .map { it.editable().toString() }
                        .extLogd()
                        .subscribe {
                            ProductData.data.companyName = it
                        },
                RxTextView.afterTextChangeEvents(inflatedView.et_productname)
                        .map { it.editable().toString() }
                        .subscribe {
                            ProductData.data.productName = it
                        },
                RxTextView.afterTextChangeEvents(inflatedView.et_productpriceafter)
                        .map { it.editable().toString() }
                        .subscribe {
                            ProductData.data.productPriceAfter = it
                        },
                RxTextView.afterTextChangeEvents(inflatedView.et_productdue)
                        .map { it.editable().toString() }
                        .subscribe {
                            ProductData.data.productDue = it
                        }
        )

        disposables.add(
                RxView.clicks(inflatedView.btn_next)
                        .subscribe {
                            changeFragment()
                        }
        )

        activity!!.seller_toolbar_tv.setText("추가하기")
    }

    private fun changeFragment() {
        BaseNavigator.gotoFragmentWithBackstack(this, MapFragment::class.java)
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }
}

fun <T> Observable<T>.extLogd(): Observable<T> {
    return this.doOnNext {
        Log.d("data=$it")
    }
}