package io.github.tturiyo.tturiyo_android.ui.customer

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.tturiyo.tturiyo_android.R
import kotlinx.android.synthetic.main.fragment_customer_home.*
import kotlinx.android.synthetic.main.fragment_customer_home.view.*

/**
 * Created by user on 2018-05-22.
 */
class CustomerHomeFragment : Fragment() {
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mView = inflater.inflate(R.layout.fragment_customer_home, container, false)
//        fragmentManager
//        activity?.fragmentManager

        (activity as AppCompatActivity).setSupportActionBar(customer_home_toolbar)
        mSectionsPagerAdapter = SectionsPagerAdapter((activity as AppCompatActivity).supportFragmentManager)

        mView.customer_home_viewpager.adapter = mSectionsPagerAdapter

        mView.customer_home_viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(mView.customer_home_tablayout))
//        mView.customer_home_tablayout.removeOnTabSelectedListener(TabLayout.OnTabSelectedListener)

        mView.customer_home_tablayout.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(mView.customer_home_viewpager))

        return mView
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            when(position) {
                0 -> {
                    CustomerHomeListFragment.newInstance(0)
                    return CustomerHomeListFragment()
                }
                1 -> {
                    CustomerHomeSearchFragment.newInstance(1)
                    return CustomerHomeListFragment()
                }
            }
            return CustomerHomeListFragment()
        }

        override fun getCount(): Int {
            return 2
        }

    }
}