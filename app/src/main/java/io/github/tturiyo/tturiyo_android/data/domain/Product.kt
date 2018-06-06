package io.github.tturiyo.tturiyo_android.data.domain

import android.net.Uri
import net.daum.mf.map.api.MapPoint
import java.util.*

/**
 * 1. uid - 사용자(기기) ID
 * 2. companyImage - 가게 이미지
 * 3. companyName - 가게 이름
 * 4. companyContact - 가게 연락처
 * 5. productName - 상품 이름
 * 6. productPriceBefore - 상품 정가
 * 7. productPriceAfter - 상품 할인가
 * 8. numberOfStock - 상품 재고 수
 * 9. productDue - 할인 기간
 * 10. location - 가게 위치(위도, 경도)
 */
data class Product (
        var uid: String = "",
        var companyImage: Int = 0,
        var companyName: String = "",
        var companyContact: String = "",
        var productName: String = "",
        var productPriceBefore: Int = 0,
        var productPriceAfter: Int = 0,
        var numberOfStock: Int = 0,
        var productDue: Date = Date(),
        var location: Location = Location()
)

data class Location (
        var latitude: Double = 0.0,
        var longitude: Double = 0.0
)

fun MapPoint.toLocation(): Location {
    return Location().apply {
        latitude = this@toLocation.mapPointGeoCoord.latitude
        longitude = this@toLocation.mapPointGeoCoord.longitude
    }
}