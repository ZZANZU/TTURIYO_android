package io.github.tturiyo.tturiyo_android.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.base.utils.toDateTimeFormat
import io.github.tturiyo.tturiyo_android.data.domain.Product
import kotlinx.android.synthetic.main.item_product.view.*
import kotlinx.android.synthetic.main.item_seller_product_expanded.view.btn_delete
import kotlinx.android.synthetic.main.item_seller_product_expanded.view.btn_modify
import kotlinx.android.synthetic.main.item_seller_product_expanded.view.tv_due

/**
 * Created by user on 2018-05-22.
 */
class SellerProductViewHolder(itemView: View,
                        onItemClicked: (Int) -> Unit,
                        private val onBtnClicked: (item: Int, btnRes: Int) -> Unit)
    : RecyclerView.ViewHolder(itemView) {

    init {
        Log.d()
        itemView.setOnClickListener {
            onItemClicked(adapterPosition)
        }
    }

    fun bind(item: Product) {
        bindWith(itemView, item)
        if (itemView.tv_due != null) {
            itemView.btn_delete.setOnClickListener {
                onBtnClicked(adapterPosition, itemView.btn_delete.id)
            }

            itemView.btn_modify.setOnClickListener {
                onBtnClicked(adapterPosition, itemView.btn_modify.id)
            }
        }
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
