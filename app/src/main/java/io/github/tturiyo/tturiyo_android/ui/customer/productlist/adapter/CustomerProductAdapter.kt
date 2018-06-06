package io.github.tturiyo.tturiyo_android.ui.customer.productlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.base.viewmodel.BaseRecyclerViewAdapter
import io.github.tturiyo.tturiyo_android.R
import io.github.tturiyo.tturiyo_android.data.domain.Product

/**
 * Created by user on 2018-05-22.
 */
class CustomerProductAdapter(private var items: List<Product> = emptyList())
    : BaseRecyclerViewAdapter<Product, CustomerProductViewHolder>() {
    override fun setItems(items: List<Product>) {
        Log.d()
        this.items = items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CustomerProductViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerProductViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_customer_product, parent,false)

        return CustomerProductViewHolder(mainView)
    }
}