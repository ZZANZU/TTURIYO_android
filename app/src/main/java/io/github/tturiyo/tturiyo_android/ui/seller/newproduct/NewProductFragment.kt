package io.github.tturiyo.tturiyo_android.ui.seller.newproduct

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
import io.github.tturiyo.tturiyo_android.databinding.FragmentSellerNewproductBinding
import io.reactivex.disposables.CompositeDisposable
import io.github.tturiyo.tturiyo_android.ui.seller.map.MapFragment
import kotlinx.android.synthetic.main.activity_seller.*


class NewProductFragment: Fragment() {
    companion object {
        fun newInstance(): NewProductFragment {
            Log.d()
            return NewProductFragment()
        }
    }

    val disposables = CompositeDisposable()
    private lateinit var inflatedView: View
    private lateinit var vm: NewProductViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d()

        val binding = DataBindingUtil.inflate<FragmentSellerNewproductBinding>(
                inflater, R.layout.fragment_seller_newproduct,
                container, false)

        inflatedView = binding.root

        vm = ViewModelProviders
                .of(this, ViewModelFactory())
                .get(NewProductViewModel::class.java)
        binding.vm = vm

        initView()

        return inflatedView
    }

    private fun initView() {
        Log.d()
        vm.attachView(inflatedView)

        activity!!.seller_toolbar_tv.setText("추가하기")
    }

    private fun changeFragment() {
        BaseNavigator.gotoFragmentWithBackstack(this, MapFragment::class.java)
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }
}