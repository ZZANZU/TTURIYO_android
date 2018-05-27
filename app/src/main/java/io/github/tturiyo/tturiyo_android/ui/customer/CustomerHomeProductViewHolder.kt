package io.github.tturiyo.tturiyo_android.ui.customer

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import io.github.tturiyo.tturiyo_android.R
import io.github.tturiyo.tturiyo_android.data.CustomerHomeProduct
import kotlinx.android.synthetic.main.item_customer_home_list.view.*

/**
 * Created by user on 2018-05-22.
 */
class CustomerHomeProductViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: CustomerHomeProduct) {

        itemView.customer_home_product_img.setImageResource(item.companyImage)
        itemView.customer_home_product_company.text = item.companyName
        itemView.customer_home_product_name.text = item.productName
        itemView.customer_home_product_cost_before.text = item.productPriceBefore
        itemView.customer_home_product_cost_after.text = item.productPriceAfter
        itemView.customer_home_product_surplus.text = item.productSurplus
    }
}