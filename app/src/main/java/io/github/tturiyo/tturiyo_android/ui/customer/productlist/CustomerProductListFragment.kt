package io.github.tturiyo.tturiyo_android.ui.customer.productlist

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.tturiyo.tturiyo_android.R
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.base.viewmodel.ViewModelFactory
import io.github.tturiyo.tturiyo_android.databinding.FragmentCustomerProductlistBinding


/**
 * Created by user on 2018-05-22.
 */
class CustomerProductListFragment: Fragment() {
    companion object {
        fun newInstance(): CustomerProductListFragment {
            return CustomerProductListFragment()
        }
    }

    private lateinit var inflatedView: View
    private val viewModel: CustomerProductListViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory())
                .get(CustomerProductListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d()

        val binding = DataBindingUtil.inflate<FragmentCustomerProductlistBinding>(
                inflater, R.layout.fragment_customer_productlist,
                container, false)

        val v = binding.root
        inflatedView = v
        viewModel.attachView(v, context!!)
        binding.vm = viewModel

        return v
    }
}