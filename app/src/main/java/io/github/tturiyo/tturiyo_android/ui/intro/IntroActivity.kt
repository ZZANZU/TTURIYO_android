package io.github.tturiyo.tturiyo_android.ui.intro

import android.Manifest
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tbruyelle.rxpermissions2.RxPermissions
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.tturiyo_android.R
import io.github.tturiyo.tturiyo_android.ui.customer.CustomerActivity
import io.github.tturiyo.tturiyo_android.ui.seller.SellerActivity
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        Log.d()

        disableBtns()
        initBtn()
        checkPermissions(onSuccess = { enableBtns() },
                onFailed = { showPermissionRequestBtn() })
    }

    private fun checkPermissions(onSuccess: () -> Unit, onFailed: () -> Unit) {
        Log.d()
        val rxPermissions = RxPermissions(this)
        rxPermissions
                .request(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                        )
                .subscribe { granted ->
                    if (granted) { // Always true pre-M
                        onSuccess()
                    } else {
                        onFailed()
                    }
                }
    }

    private fun enableBtns() {
        Log.d()
        // TODO 180602 jyp removePermissionRequestBtn
        // TODO 180602 jyp implement enableBtns
    }

    private fun disableBtns() {
        Log.d()
        // TODO 180602 jyp implement
    }

    private fun showPermissionRequestBtn() {
        Log.d()
        // TODO 180602 jyp implement
    }

    private fun initBtn() {
        Log.d()

        btn_customer.setOnClickListener {
            gotoCustomerActivity()
        }

        btn_seller.setOnClickListener {
            gotoSellerActivity()
        }
    }

    private fun gotoSellerActivity() {
        gotoActivity(SellerActivity::class.java)
    }

    private fun gotoCustomerActivity() {
        gotoActivity(CustomerActivity::class.java)
    }

    private fun gotoActivity(clazz: Class<out AppCompatActivity>) {
        Log.d()

        val intent = Intent(this, clazz)
        startActivity(intent)
    }
}
