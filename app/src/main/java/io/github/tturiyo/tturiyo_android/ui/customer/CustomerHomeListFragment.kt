package io.github.tturiyo.tturiyo_android.ui.customer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.tturiyo.tturiyo_android.R
import io.github.tturiyo.tturiyo_android.data.domain.Product
import kotlinx.android.synthetic.main.fragment_customer_home_list.view.*
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.tturiyo_android.data.repo.ProductRepo
import io.reactivex.subjects.BehaviorSubject


/**
 * Created by user on 2018-05-22.
 */
class CustomerHomeListFragment: Fragment() {
    val mProductItems :BehaviorSubject<List<Product>> = BehaviorSubject.createDefault(emptyList())
    lateinit var mCustomerHomeProductAdapter : CustomerHomeProductAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mView = inflater.inflate(R.layout.fragment_customer_home_list, container, false)

        initData()
        mCustomerHomeProductAdapter = CustomerHomeProductAdapter(mProductItems)

        mView.customer_home_list_rv.layoutManager = LinearLayoutManager(activity)
        mView.customer_home_list_rv.adapter = mCustomerHomeProductAdapter

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

        fun newInstance(sectionNumber: Int): CustomerHomeListFragment {
            val fragment = CustomerHomeListFragment()
//            val args = Bundle()
//            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
//            fragment.arguments = args
            return fragment
        }
    }
}