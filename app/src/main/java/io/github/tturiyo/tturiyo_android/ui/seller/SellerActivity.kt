package io.github.tturiyo.tturiyo_android.ui.seller

import android.os.Bundle
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.base.ui.BaseFragmentActivity
import io.github.tturiyo.tturiyo_android.R
import io.github.tturiyo.tturiyo_android.ui.seller.products.SellerProductListFragment


class SellerActivity : BaseFragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        contentFrameId = R.id.contentFrame
        initialFragmentClazz = SellerProductListFragment::class.java

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller)
        Log.d()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
