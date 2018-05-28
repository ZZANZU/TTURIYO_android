package io.github.tturiyo.tturiyo_android.ui.customer

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.tturiyo.tturiyo_android.R
import io.github.tturiyo.tturiyo_android.data.CustomerHomeProduct

/**
 * Created by user on 2018-05-22.
 */
class CustomerHomeProductAdapter(private var productItems : ArrayList<CustomerHomeProduct>)
    : RecyclerView.Adapter<CustomerHomeProductViewHolder>() {
    override fun onBindViewHolder(holder: CustomerHomeProductViewHolder, position: Int) {
        holder.bind(productItems[position])
    }

    override fun getItemCount(): Int = productItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerHomeProductViewHolder {
        val mainView : View = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_customer_home_list, parent,false)

        return CustomerHomeProductViewHolder(mainView)
    }
}