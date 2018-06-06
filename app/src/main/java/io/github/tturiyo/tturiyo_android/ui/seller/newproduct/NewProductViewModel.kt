package io.github.tturiyo.tturiyo_android.ui.seller.newproduct

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.view.View
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.base.debug.logd
import io.github.tturiyo.base.viewmodel.toObservable
import io.github.tturiyo.tturiyo_android.ui.seller.ProductData
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_seller_newproduct.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class NewProductViewModel() : ViewModel() {
    private val disposables = CompositeDisposable()
    val companyName = ObservableField<String>("")
    val companyContact = ObservableField<String>("")
    val productName = ObservableField<String>("")
    val productPriceBefore = ObservableField<String>("0")
    val productPriceAfter = ObservableField<String>("0")
    val numberOfStock = ObservableField<String>("0")
    val productDue = ObservableField<String>(Date().toString())

    init {
    }

    // TODO jyp 180605 remove
    private fun putTemporalValues() {
        Log.d()
        companyName.set("양이컴퍼니")
        companyContact.set("02-1234-1234")
        productName.set("양사료")
        productPriceBefore.set("2000")
        productPriceAfter.set("1000")
        numberOfStock.set("8")
        productDue.set("2018.06.05. 18:00")
    }

    fun attachView(inflatedView: View) {
        // TODO jyp 180605 remove
        putTemporalValues()

        disposables.addAll(
                RxTextView.afterTextChangeEvents(inflatedView.et_companyname)
                        .skipInitialValue()
                        .map { it.editable().toString() }
                        .logd()
                        .subscribe {
                            companyName.set(it)
                        },
                companyName.toObservable().subscribe {
                    ProductData.data.companyName = it
                }
        )

        disposables.addAll(
                RxTextView.afterTextChangeEvents(inflatedView.et_companycontact)
                        .skipInitialValue()
                        .map { it.editable().toString() }
                        .subscribe {
                            companyContact.set(it)
                        },
                companyContact.toObservable().subscribe {
                    ProductData.data.companyContact = it
                }
        )

        disposables.addAll(
                RxTextView.afterTextChangeEvents(inflatedView.et_productname)
                        .skipInitialValue()
                        .map { it.editable().toString() }
                        .subscribe {
                            productName.set(it)
                        },
                productName.toObservable().subscribe {
                    ProductData.data.productName = it
                }
        )

        disposables.addAll(
                RxTextView.afterTextChangeEvents(inflatedView.et_productpricebefore)
                        .skipInitialValue()
                        .map { it.editable().toString() }
                        .filter { it.toIntOrNull() != null }
                        .subscribe {
                            productPriceBefore.set(it)
                        },
                productPriceBefore.toObservable().subscribe {
                    ProductData.data.productPriceBefore = it.toInt()
                }
        )

        disposables.addAll(
                RxTextView.afterTextChangeEvents(inflatedView.et_productpriceafter)
                        .skipInitialValue()
                        .map { it.editable().toString() }
                        .filter { it.toIntOrNull() != null }
                        .subscribe {
                            productPriceAfter.set(it)
                        },
                productPriceAfter.toObservable().subscribe {
                    ProductData.data.productPriceAfter = it.toInt()
                }
        )

        disposables.addAll(
                RxTextView.afterTextChangeEvents(inflatedView.et_numberofstock)
                        .skipInitialValue()
                        .map { it.editable().toString() }
                        .filter { it.toIntOrNull() != null }
                        .subscribe {
                            numberOfStock.set(it)
                        },
                numberOfStock.toObservable().subscribe {
                    ProductData.data.numberOfStock = it.toInt()
                }
        )

        disposables.addAll(
                RxTextView.afterTextChangeEvents(inflatedView.et_numberofstock)
                        .skipInitialValue()
                        .map { it.editable().toString() }
                        .filter { it.toIntOrNull() != null }
                        .subscribe {
                            numberOfStock.set(it)
                        },
                numberOfStock.toObservable().subscribe {
                    ProductData.data.numberOfStock = it.toInt()
                }
        )

        disposables.addAll(
                RxTextView.afterTextChangeEvents(inflatedView.et_productdue)
                        .skipInitialValue()
                        .map { it.editable().toString() }
                        .map { Date() }
                        .map { it.toString() }
                        .subscribe {
                            productDue.set(it)
                        },
                productDue.toObservable().subscribe {
                    // TODO jyp 180605 시간고르는창 띄우기
//                    ProductData.data.productDue = SimpleDateFormat().parse(it)
                }
        )


        disposables.add(
                RxView.clicks(inflatedView.btn_next)
                        .subscribe {
//                            changeFragment()
                        }
        )

    }

    override fun onCleared() {
        Log.d()
        disposables.dispose()

        super.onCleared()
    }
}