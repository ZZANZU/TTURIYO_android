package io.github.tturiyo.base.viewmodel

import android.databinding.ObservableField
import io.reactivex.Observable

fun <T> ObservableField<T>.toObservable(): Observable<T> {
    return io.reactivex.Observable.fromPublisher { asyncEmitter ->
        val callback = object : android.databinding.Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(dataBindingObservable: android.databinding.Observable, propertyId: Int) {
                if (dataBindingObservable === this@toObservable) {
                    asyncEmitter.onNext(this@toObservable.get())
                }
            }
        }
        this@toObservable.addOnPropertyChangedCallback(callback)

        // initial value
        val initialValue = this@toObservable.get()
        if (initialValue != null) {
            asyncEmitter.onNext(initialValue)
        }
    }
}
