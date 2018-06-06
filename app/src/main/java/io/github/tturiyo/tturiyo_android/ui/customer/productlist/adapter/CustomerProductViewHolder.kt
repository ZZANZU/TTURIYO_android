package io.github.tturiyo.tturiyo_android.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.base.utils.toDateTimeFormat
import io.github.tturiyo.tturiyo_android.data.domain.Product
import kotlinx.android.synthetic.main.item_product.view.*
import kotlinx.android.synthetic.main.item_product_expanded.view.btn_buy
import kotlinx.android.synthetic.main.item_product_expanded.view.tv_due

/**
 * Created by user on 2018-05-22.
 */
class CustomerProductViewHolder(itemView: View,
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
            itemView.btn_buy.setOnClickListener {
                onBtnClicked(adapterPosition, itemView.btn_buy.id)
            }
        }
    }
}