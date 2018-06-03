package io.github.tturiyo.base.debug

import io.reactivex.Observable


fun <T> Observable<T>.extLogd(): Observable<T> {
    return this.doOnNext {
        io.github.tturiyo.base.debug.Log.d("data=$it")
    }
}
