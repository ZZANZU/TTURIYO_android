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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.tturiyo.tturiyo_android.R
import kotlinx.android.synthetic.main.fragment_customer_home.*

/**
 * Created by user on 2018-05-22.
 */
class CustomerHomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mView = inflater.inflate(R.layout.fragment_customer_home, container, false)

        configureTabLayout(customer_home_tablayout!!)

        return mView
    }

    private fun configureTabLayout(tabLayout: TabLayout) {
        tabLayout.addTab(tabLayout.newTab().setText("실시간 목록"))
        tabLayout.addTab(tabLayout.newTab().setText("찾아보기"))

        val adapter = TabPagerAdapter(activity!!.supportFragmentManager, tabLayout.tabCount) // !! : 흠

        customer_home_viewpager.adapter = adapter
        customer_home_viewpager.addOnPageChangeListener(
                TabLayout.TabLayoutOnPageChangeListener(customer_home_tablayout))

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab) {
                customer_home_viewpager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    companion object {
        val PAGE_NUM = "PAGE_NUM"
        fun newInstance(page: Int): CustomerHomeFragment {
            val fragment = CustomerHomeFragment()
            val args = Bundle()
            args.putInt(PAGE_NUM, page)
            fragment.setArguments(args)
            return fragment
        }
    }
}

class TabPagerAdapter(fm: FragmentManager, private var tabCount: Int) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        when(position) {
            0 -> return CustomerHomeListFragment()
            1 -> return CustomerHomeSearchFragment()

            else -> return null
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}