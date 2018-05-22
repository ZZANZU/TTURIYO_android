package io.github.tturiyo.tturiyo_android.ui.customer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.tturiyo_android.R
import kotlinx.android.synthetic.main.activity_customer.*

class CustomerActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer)
        Log.d()

        addFragment(CustomerHomeFragment())

        tab_customer_home.setOnClickListener(this)
        tab_customer_surplus.setOnClickListener(this)
        tab_customer_payment.setOnClickListener(this)
        tab_customer_setting.setOnClickListener(this)
    }

    fun addFragment(mFragment: Fragment) {
        val mFragmentManager = supportFragmentManager
        val mTransaction = mFragmentManager.beginTransaction()

        mTransaction.add(R.id.customer_fragment, mFragment)
        mTransaction.addToBackStack(null)
        mTransaction.commit()
    }

    fun replaceFragment(mFragment: Fragment) {
        val mFragmentManager = supportFragmentManager
        val mTransaction = mFragmentManager.beginTransaction()

        mTransaction.replace(R.id.customer_fragment, mFragment)
        mTransaction.commit()
    }

    override fun onClick(v : View?) {
        when(v) {
            tab_customer_home -> {
                replaceFragment(CustomerHomeFragment())
            }
            tab_customer_surplus -> {
                replaceFragment(CustomerSurplusFragment())
            }
            tab_customer_payment -> {
                replaceFragment(CustomerPaymentFragment())
            }
            tab_customer_setting -> {
                replaceFragment(CustomerSettingFragment())
            }
        }
    }
}
