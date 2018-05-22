package io.github.tturiyo.tturiyo_android.ui.customer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.tturiyo.tturiyo_android.R
import kotlinx.android.synthetic.main.fragment_customer_home_list.*

/**
 * Created by user on 2018-05-22.
 */
class CustomerHomeListFragment: Fragment() {
    lateinit var mProductItems : ArrayList<CustomerHomeProductData>
    lateinit var mCustomerHomeProductAdapter : CustomerHomeProductAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mView = inflater.inflate(R.layout.fragment_customer_home_list, container, false)

        return mView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mProductItems = ArrayList()

        mProductItems.add(CustomerHomeProductData(R.drawable.company_img, "떡뽀이"
                , "4000", "3000", "10개/23개", R.drawable.like_off_img))
        mProductItems.add(CustomerHomeProductData(R.drawable.company_img, "떡뽀이"
                , "4000", "3000", "10개/23개", R.drawable.like_off_img))
        mProductItems.add(CustomerHomeProductData(R.drawable.company_img, "떡뽀이"
                , "4000", "3000", "10개/23개", R.drawable.like_off_img))
        mProductItems.add(CustomerHomeProductData(R.drawable.company_img, "떡뽀이"
                , "4000", "3000", "10개/23개", R.drawable.like_off_img))


        mCustomerHomeProductAdapter = CustomerHomeProductAdapter(mProductItems)

        customer_home_list_rv.layoutManager = LinearLayoutManager(activity)
        customer_home_list_rv.adapter = mCustomerHomeProductAdapter
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        fun newInstance(sectionNumber: Int): CustomerHomeListFragment {
            val fragment = CustomerHomeListFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }
}