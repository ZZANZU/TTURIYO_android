package io.github.tturiyo.tturiyo_android.ui.seller.productlist

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.base.ui.BaseNavigator
import io.github.tturiyo.base.viewmodel.ViewModelFactory
import io.github.tturiyo.tturiyo_android.R
import io.github.tturiyo.tturiyo_android.databinding.FragmentSellerProductlistBinding
import io.github.tturiyo.tturiyo_android.ui.seller.newproduct.NewProductFragment
import kotlinx.android.synthetic.main.activity_seller.*

class SellerProductListFragment : Fragment(), SellerProductListNavigator {
    companion object {
        fun newInstance(): SellerProductListFragment {
            Log.d()
            return SellerProductListFragment()
        }
    }

    private lateinit var inflatedView: View
    private val viewModel: SellerProductListViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory(this))
                .get(SellerProductListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d()

        val binding = DataBindingUtil.inflate<FragmentSellerProductlistBinding>(
                inflater, R.layout.fragment_seller_productlist,
                container, false)
        val v = binding.root
        inflatedView = v
        viewModel.attachView(v, context!!)
        binding.vm = viewModel

        return inflatedView
    }

    override fun onResume() {
        super.onResume()
        Log.d()
        setFragmentName("내 떠리요")
    }

    private fun setFragmentName(s: String) {
        Log.d()
        activity!!.seller_toolbar_tv.text = s
    }

    override fun gotoNewProductFragment() {
        Log.d()
        BaseNavigator.gotoFragmentWithBackstack(this, NewProductFragment::class.java)
    }

    override fun onDestroy() {
        Log.d()

        super.onDestroy()
    }
}
