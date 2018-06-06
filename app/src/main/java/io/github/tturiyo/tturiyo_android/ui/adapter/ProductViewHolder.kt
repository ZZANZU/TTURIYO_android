package io.github.tturiyo.tturiyo_android.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.base.utils.toDateTimeFormat
import io.github.tturiyo.tturiyo_android.data.domain.Product
import kotlinx.android.synthetic.main.item_product.view.*
import kotlinx.android.synthetic.main.item_product_expanded.view.tv_due

/**
 * Created by user on 2018-05-22.
 */
class ProductViewHolder(itemView: View,
                        onItemClicked: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {

    init {
        Log.d()
        itemView.setOnClickListener {
            onItemClicked(adapterPosition)
        }
    }

    fun bind(item: Product) {
        bindWith(itemView, item)
    }
}

fun bindWith(view: View, item: Product) {
    view.customer_home_product_company.text = item.companyName
    view.tv_productname.text = item.productName
    view.customer_home_product_cost_before.text = item.productPriceBefore.toString()
    view.customer_home_product_cost_after.text = item.productPriceAfter.toString()
    view.customer_home_product_surplus.text = "${item.currentStock}/${item.numberOfStock}"
    if (view.tv_due != null) {
        view.tv_due.text = "${item.productDue.toDateTimeFormat("yy.MM.dd HH:mm")} 까지"
    }
}
