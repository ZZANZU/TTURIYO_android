package io.github.tturiyo.tturiyo_android.ui.seller

import io.github.tturiyo.tturiyo_android.data.domain.Product

object ProductData {
    fun getAndClear(): Product {
        val result = data
        data = Product()

        return result
    }

    var data: Product = Product()
}
