package io.github.tturiyo.tturiyo_android.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.base.viewmodel.BaseRecyclerViewAdapter
import io.github.tturiyo.tturiyo_android.R
import io.github.tturiyo.tturiyo_android.data.domain.Product
import io.github.tturiyo.tturiyo_android.ui.seller.productlist.SellerProductListNavigator

/**
 * Created by user on 2018-05-22.
 */
class SellerProductAdapter(private var items: List<Product> = emptyList(),
                           private val navigator: SellerProductListNavigator)
    : BaseRecyclerViewAdapter<Product, SellerProductViewHolder>() {
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

    override fun onBindViewHolder(holder: SellerProductViewHolder, position: Int) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SellerProductViewHolder {
        val mainView: View = when (viewType) {
            UNEXPANDED -> LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_product, parent, false)
            else -> LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_seller_product_expanded, parent, false)
        }

        return SellerProductViewHolder(mainView,
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
                        R.id.btn_modify -> navigator.modify(items[item])
                        R.id.btn_delete -> {
                            navigator.delete(items[item])
                            focusedItem = -1
                        }
                    }
                })
    }
}