package io.github.tturiyo.tturiyo_android.ui.customer.productlistmap

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.base.viewmodel.ViewModelFactory
import io.github.tturiyo.tturiyo_android.R
import io.github.tturiyo.tturiyo_android.databinding.FragmentCustomerProductlistmapBinding

/**
 * Created by user on 2018-05-22.
 */
class ProductListMapFragment: Fragment() {
    companion object {
        fun newInstance(): ProductListMapFragment {
            return ProductListMapFragment()
        }
    }

    private lateinit var inflatedView: View
    private val viewModel: ProductListMapViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory())
                .get(ProductListMapViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d()
        val binding = DataBindingUtil.inflate<FragmentCustomerProductlistmapBinding>(
                inflater, R.layout.fragment_customer_productlistmap,
                container, false)

        val v = binding.root
        inflatedView = v
        viewModel.attachView(v, context!!)
        binding.vm = viewModel

        return v
    }

    override fun onResume() {
        super.onResume()
        Log.d()
    }

    override fun onPause() {
        super.onPause()
        Log.d()
    }

    override fun onDestroy() {
        Log.d()
        super.onDestroy()
    }

    override fun onDestroyView() {
        Log.d()
        super.onDestroyView()
    }
}