package io.github.tturiyo.tturiyo_android.ui.seller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.tturiyo_android.R
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_seller.*

class SellerActivity : AppCompatActivity() {
    val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller)
        Log.d()

        initView()
    }

    private fun initView() {
        Log.d()

        disposables.addAll(
                RxTextView.afterTextChangeEvents(et_companyname)
                        .map { it.editable().toString() }
                        .extLogd()
                        .subscribe {
                            ProductData.companyname = it
                        },
                RxTextView.afterTextChangeEvents(et_companylocation)
                        .map { it.editable().toString() }
                        .subscribe {
                            ProductData.companylocation = it
                        },
                RxTextView.afterTextChangeEvents(et_productname)
                        .map { it.editable().toString() }
                        .subscribe {
                            ProductData.productname = it
                        },
                RxTextView.afterTextChangeEvents(et_productpriceafter)
                        .map { it.editable().toString() }
                        .subscribe {
                            ProductData.productpriceafter = it
                        },
                RxTextView.afterTextChangeEvents(et_productdue)
                        .map { it.editable().toString() }
                        .subscribe {
                            ProductData.productdue = it
                        }
                )

        disposables.add(
                RxView.clicks(btn_submit)
                        .subscribe {
                            saveData()
                        }
        )
    }

    private fun saveData() {
        Log.d()
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