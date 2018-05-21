package io.github.tturiyo.tturiyo_android.ui.intro

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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

        initBtn()
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
