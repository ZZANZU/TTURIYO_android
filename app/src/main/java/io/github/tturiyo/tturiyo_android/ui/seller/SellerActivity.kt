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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.github.tturiyo.tturiyo_android.data.file.getUuid


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
                            ProductData.data.companyName = it
                        },
//                RxTextView.afterTextChangeEvents(et_companylocation)
//                        .map { it.editable().toString() }
//                        .subscribe {
//                            ProductData.data.companylocation = it
//                        },
                RxTextView.afterTextChangeEvents(et_productname)
                        .map { it.editable().toString() }
                        .subscribe {
                            ProductData.data.productName = it
                        },
                RxTextView.afterTextChangeEvents(et_productpriceafter)
                        .map { it.editable().toString() }
                        .subscribe {
                            ProductData.data.productPriceAfter = it
                        }
//                RxTextView.afterTextChangeEvents(et_productdue)
//                        .map { it.editable().toString() }
//                        .subscribe {
//                            ProductData.data.productdue = it
//                        }
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

        val productsRef = FirebaseDatabase.getInstance().getReference("products")
        ProductData.data.uid = getUuid()
        val push: DatabaseReference = productsRef.push()
        productsRef.child(push.key).setValue(ProductData.data)

        val userRef = FirebaseDatabase.getInstance().getReference("users")
        userRef.child(getUuid()).child("products").push().setValue(push.key)
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
