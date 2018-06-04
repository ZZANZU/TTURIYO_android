package io.github.tturiyo.tturiyo_android.data.domain

import io.github.tturiyo.base.debug.Log
import net.daum.mf.map.api.MapPoint

/**
 * Created by user on 2018-05-22.
 * companyImage - 가게 이미지
 * companyName - 가게 이름
 * productName - 상품 이름
 * productPriceBefore - 상품 정가, Int?
 * productPriceAfter - 상품 할인가, Int?
 * productSurplus - 상품 재고
 */
data class Product (
        var uid: String = "",
        var companyImage: Int = 0,
        var companyName: String = "",
        var productName: String = "",
        var productPriceBefore: String = "",
        var productPriceAfter: String = "",
        var productDue: String ="",
        var productSurplus: String = "",
        var productLike: Int = 0,
        var location: Location = Location()
)

data class Location (
        var latitude: Double = 0.0,
        var longitude: Double = 0.0
)

fun void() {
    Log.d(2.plus2())
}

fun Int.plus2(): Int {
    return this + 2
}

fun MapPoint.toLocation(): Location {
    return Location().apply {
        latitude = this@toLocation.mapPointGeoCoord.latitude
        longitude = this@toLocation.mapPointGeoCoord.longitude
    }
}