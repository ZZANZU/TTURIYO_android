package io.github.tturiyo.tturiyo_android.ui.customer.productlist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.tturiyo.tturiyo_android.R
import io.github.tturiyo.tturiyo_android.data.domain.Product
import kotlinx.android.synthetic.main.fragment_customer_home_list.view.*
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.tturiyo_android.data.repo.ProductRepo
import io.github.tturiyo.tturiyo_android.ui.customer.productlist.adapter.CustomerProductAdapter
import io.reactivex.subjects.BehaviorSubject


/**
 * Created by user on 2018-05-22.
 */
class CustomerProductListFragment: Fragment() {
    val mProductItems :BehaviorSubject<List<Product>> = BehaviorSubject.createDefault(emptyList())
    lateinit var mCustomerProductAdapter : CustomerProductAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mView = inflater.inflate(R.layout.fragment_customer_home_list, container, false)

        initData()
        mCustomerProductAdapter = CustomerProductAdapter(mProductItems)

        mView.customer_home_list_rv.layoutManager = LinearLayoutManager(activity)
        mView.customer_home_list_rv.adapter = mCustomerProductAdapter

        return mView
    }

    private fun initData() {
        Log.d()

        ProductRepo.getListAsObservable().subscribe {
            // do something
            mProductItems.onNext(it)
        }
    }

    companion object {
        private val ARG_SECTION_NUMBER = "section_number"

        fun newInstance(sectionNumber: Int): CustomerProductListFragment {
            val fragment = CustomerProductListFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }
}