package io.github.tturiyo.tturiyo_android.ui.customer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.tturiyo.tturiyo_android.R
import io.github.tturiyo.tturiyo_android.data.Product
import kotlinx.android.synthetic.main.fragment_customer_home_list.view.*
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.github.tturiyo.base.debug.Log
import io.reactivex.subjects.BehaviorSubject


/**
 * Created by user on 2018-05-22.
 */
class CustomerHomeListFragment: Fragment() {
    val mProductItems :BehaviorSubject<List<Product>> = BehaviorSubject.createDefault(emptyList())
    lateinit var mCustomerHomeProductAdapter : CustomerHomeProductAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mView = inflater.inflate(R.layout.fragment_customer_home_list, container, false)

        bindWithDataSource()
        mCustomerHomeProductAdapter = CustomerHomeProductAdapter(mProductItems)

        mView.customer_home_list_rv.layoutManager = LinearLayoutManager(activity)
        mView.customer_home_list_rv.adapter = mCustomerHomeProductAdapter

        return mView
    }

    private fun bindWithDataSource() {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val result = ArrayList<Product>()

                Log.d(dataSnapshot)
                for (each in dataSnapshot.children) {
                    val eachPojo: Product = each.getValue(Product::class.java)!!
                    result.add(eachPojo)
                }

                mProductItems.onNext(result)
                Log.d(result)
                // ...
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.d(databaseError.toException())
                // ...
            }
        }
        val ref = FirebaseDatabase.getInstance().getReference("products")
        ref.addValueEventListener(postListener)
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