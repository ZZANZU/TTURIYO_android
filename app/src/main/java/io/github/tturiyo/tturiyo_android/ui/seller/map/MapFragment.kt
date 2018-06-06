package io.github.tturiyo.tturiyo_android.ui.seller.map

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.base.debug.assertDebug
import io.github.tturiyo.base.ui.BaseFragment
import io.github.tturiyo.base.ui.BaseNavigator
import io.github.tturiyo.base.viewmodel.ViewModelFactory
import io.github.tturiyo.tturiyo_android.R
import io.github.tturiyo.tturiyo_android.databinding.FragmentSellerMapBinding
import io.github.tturiyo.tturiyo_android.ui.seller.SellerNavigator
import io.github.tturiyo.tturiyo_android.ui.seller.productlist.SellerProductListFragment
import kotlinx.android.synthetic.main.activity_seller.*

class MapFragment : BaseFragment(), MapNavigator {
    companion object {
        fun newInstance(): MapFragment {
            Log.d()
            return MapFragment()
        }
    }

    private lateinit var inflatedView: View
    private val viewModel: MapViewModel by lazy {
        ViewModelProviders.of(this,
                ViewModelFactory(this))
                .get(MapViewModel::class.java)
    }
    private var navigator: SellerNavigator? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d()

        val binding = DataBindingUtil.inflate<FragmentSellerMapBinding>(
                inflater, R.layout.fragment_seller_map,
                container, false)
        val v = binding.root
        inflatedView = v
        viewModel.attachView(v)
        binding.vm = viewModel

        initTitle()

        return v
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        assertDebug(context is SellerNavigator)
        navigator = context as SellerNavigator
    }

    private fun initTitle() {
        Log.d()

        activity!!.seller_toolbar_tv.text = "위치 확인"
    }

    override fun onDestroy() {
        Log.d()

        super.onDestroy()
    }

    override fun backPressed() {
        Log.d()
        navigator?.backPressed()
    }

    override fun gotoSellerProductListFragmentWithClearingBackstack() {
        Log.d()
        BaseNavigator.gotoFragmentWithClearingBackstack(this, SellerProductListFragment::class.java)
    }
}
