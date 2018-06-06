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
import java.util.*
import android.app.TimePickerDialog
import android.content.Context


class NewProductViewModel(private var navigator: NewProductNavigator) : ViewModel() {
    private val disposables = CompositeDisposable()

    val companyName = ObservableField<String>("")
    val companyContact = ObservableField<String>("")
    val productName = ObservableField<String>("")
    val productPriceBefore = ObservableField<String>("0")
    val productPriceAfter = ObservableField<String>("0")
    val numberOfStock = ObservableField<String>("0")
    val productDue = ObservableField<Long>(0)

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
        productDue.set(Calendar.getInstance().timeInMillis)
    }

    fun attachView(inflatedView: View, ctx: Context) {
        // TODO jyp 180605 remove
        putTemporalValues()

        disposables.addAll(
                RxTextView.afterTextChangeEvents(inflatedView.et_companyName)
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
                RxTextView.afterTextChangeEvents(inflatedView.et_companyContact)
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
                RxTextView.afterTextChangeEvents(inflatedView.et_productName)
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

        disposables.add(
                RxView.clicks(inflatedView.et_productdue)
                        .subscribe {
                            TimePickerDialog(ctx,
                                    TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                                        val calendar = Calendar.getInstance()
                                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                                        calendar.set(Calendar.MINUTE, minute)
                                        productDue.set(calendar.timeInMillis)
                                    }, 0,
                                    0, false).show()
                        }
        )

        disposables.add(
                RxView.clicks(inflatedView.btn_next)
                        .subscribe {
                            navigator.gotoMapFragment()
                        }
        )
    }

    override fun onCleared() {
        Log.d()
        disposables.dispose()

        super.onCleared()
    }
}