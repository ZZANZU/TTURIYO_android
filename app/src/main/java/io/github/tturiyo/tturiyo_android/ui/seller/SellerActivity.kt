package io.github.tturiyo.tturiyo_android.ui.seller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.tturiyo_android.R
import io.reactivex.disposables.CompositeDisposable
import io.github.tturiyo.base.ui.BaseNavigator
import io.github.tturiyo.tturiyo_android.ui.seller.map.MapFragment


class SellerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller)
        Log.d()

        val savedFragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
        if (savedFragment == null) {
            initFragment()
        }
    }

    private fun initFragment() {
        BaseNavigator.gotoFragmentWithActivity(this, MapFragment::class.java, R.id.contentFrame)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
