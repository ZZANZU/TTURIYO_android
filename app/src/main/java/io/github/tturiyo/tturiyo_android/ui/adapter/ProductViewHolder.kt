package io.github.tturiyo.tturiyo_android.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import io.github.tturiyo.tturiyo_android.data.domain.Product
import kotlinx.android.synthetic.main.item_product.view.*

/**
 * Created by user on 2018-05-22.
 */
class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: Product) {
        bindWith(itemView, item)
    }
}

fun bindWith(view: View, item: Product) {
    view.customer_home_product_company.text = item.companyName
    view.customer_home_product_name.text = item.productName
    view.customer_home_product_cost_before.text = item.productPriceBefore.toString()
    view.customer_home_product_cost_after.text = item.productPriceAfter.toString()
    view.customer_home_product_surplus.text = item.numberOfStock.toString()
}