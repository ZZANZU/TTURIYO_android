package io.github.tturiyo.tturiyo_android.ui.adapter

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
class ProductAdapter(private var items: List<Product> = emptyList())
    : BaseRecyclerViewAdapter<Product, ProductViewHolder>() {
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

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val mainView: View = when (viewType) {
            UNEXPANDED -> LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_product, parent, false)
            else -> LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_product_expanded, parent, false)
        }

        return ProductViewHolder(mainView, {
            focusedItem = it
            notifyDataSetChanged()
        })
    }
}