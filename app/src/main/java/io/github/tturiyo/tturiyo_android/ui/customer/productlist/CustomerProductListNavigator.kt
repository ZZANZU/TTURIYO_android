package io.github.tturiyo.tturiyo_android.ui.customer.productlist

import io.github.tturiyo.tturiyo_android.data.domain.Product

interface CustomerProductListNavigator {
    fun buy(product: Product)
}