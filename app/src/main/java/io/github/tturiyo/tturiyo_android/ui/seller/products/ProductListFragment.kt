package io.github.tturiyo.tturiyo_android.ui.seller.products

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.view.RxView
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.base.ui.BaseNavigator
import io.github.tturiyo.tturiyo_android.R
import io.github.tturiyo.tturiyo_android.ui.seller.SellerProductRegisterFragment
import io.github.tturiyo.tturiyo_android.ui.seller.map.MapFragment
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_seller.*
import kotlinx.android.synthetic.main.fragment_seller_productlist.*
import kotlinx.android.synthetic.main.fragment_seller_productlist.view.*

class ProductListFragment : Fragment() {
    companion object {
        fun newInstance(): ProductListFragment {
            Log.d()
            return ProductListFragment()
        }
    }

    private val disposables = CompositeDisposable()
    private lateinit var inflatedView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d()

        inflatedView = inflater.inflate(R.layout.fragment_seller_productlist, container, false)
        initView()

        return inflatedView
    }

    private fun initView() {
        Log.d()

        disposables.add(
                RxView.clicks(inflatedView.btn_add)
                        .subscribe {
                            Log.d("BaseNavigator.gotoFragmentWithBackstack(this, MapFragment::class.java)")
                            BaseNavigator.gotoFragmentWithBackstack(this, SellerProductRegisterFragment::class.java)
                        }
        )

        activity!!.seller_toolbar_tv.setText("내 떠리요")
    }

    override fun onDestroy() {
        Log.d()

        disposables.dispose()
        super.onDestroy()
    }
}
