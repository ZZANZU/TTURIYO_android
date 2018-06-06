package io.github.tturiyo.tturiyo_android.ui.seller.productlist

import io.github.tturiyo.tturiyo_android.data.domain.Product

interface SellerProductListNavigator {
    fun gotoNewProductFragment()
    fun modify(product: Product)
    fun delete(product: Product)
}