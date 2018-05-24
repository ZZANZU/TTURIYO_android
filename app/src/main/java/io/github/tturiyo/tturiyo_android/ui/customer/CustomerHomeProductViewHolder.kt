package io.github.tturiyo.tturiyo_android.ui.customer

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import io.github.tturiyo.tturiyo_android.R
import kotlinx.android.synthetic.main.item_customer_home_list.view.*

/**
 * Created by user on 2018-05-22.
 */
class CustomerHomeProductViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var companyImage : ImageView = itemView!!.findViewById(R.id.customer_home_product_img)
    var companyName : TextView = itemView!!.findViewById(R.id.customer_home_product_company)
    var productPriceBefore : TextView = itemView!!.findViewById(R.id.customer_home_product_cost_before)
    var productPriceAfter : TextView = itemView!!.findViewById(R.id.customer_home_product_cost_after)
    var productSurplus : TextView = itemView!!.findViewById(R.id.customer_home_product_surplus)
}