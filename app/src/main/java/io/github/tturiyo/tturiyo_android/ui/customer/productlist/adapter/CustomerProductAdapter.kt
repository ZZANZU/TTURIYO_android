package io.github.tturiyo.tturiyo_android.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.base.viewmodel.BaseRecyclerViewAdapter
import io.github.tturiyo.tturiyo_android.R
import io.github.tturiyo.tturiyo_android.data.domain.Product
import io.github.tturiyo.tturiyo_android.ui.customer.productlist.CustomerProductListNavigator
import io.github.tturiyo.tturiyo_android.ui.seller.productlist.SellerProductListNavigator

/**
 * Created by user on 2018-05-22.
 */
class CustomerProductAdapter(private var items: List<Product> = emptyList(),
                             private val navigator: CustomerProductListNavigator)
    : BaseRecyclerViewAdapter<Product, CustomerProductViewHolder>() {
    companion object {
        const val UNEXPANDED = 0
        const val EXPANDED = 1
    }

    private var focusedItem = -1

    override fun setItems(items: List<Product>) {
        Log.d()
        this.items = items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CustomerProductViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == focusedItem) {
            EXPANDED
        } else {
            UNEXPANDED
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerProductViewHolder {
        val mainView: View = when (viewType) {
            UNEXPANDED -> LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_product, parent, false)
            else -> LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_product_expanded, parent, false)
        }

        return CustomerProductViewHolder(mainView,
                onItemClicked = {
                    if (focusedItem == it) {
                        focusedItem = -1
                    } else {
                        focusedItem = it
                    }
                    notifyDataSetChanged()
                },
                onBtnClicked = { item: Int, btnRes: Int ->
                    when (btnRes) {
                        R.id.btn_buy -> navigator.buy(items[item])
                    }
                })
    }
}