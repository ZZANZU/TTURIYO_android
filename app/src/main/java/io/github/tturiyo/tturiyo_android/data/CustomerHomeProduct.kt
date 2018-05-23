package io.github.tturiyo.tturiyo_android.data

/**
 * Created by user on 2018-05-22.
 * companyImage - 가게 이미지
 * companyName - 가게 이름
 * productPriceBefore - 상품 정가, Int?
 * productPriceAfter - 상품 할인가, Int?
 * productSurplus - 상품 재고
 * productLike - 상품 like 여부
 */
data class CustomerHomeProduct (
        var companyImage : Int,
        var companyName : String,
        var productPriceBefore : String,
        var productPriceAfter : String,
        var productSurplus : String,
        var productLike : Int
)