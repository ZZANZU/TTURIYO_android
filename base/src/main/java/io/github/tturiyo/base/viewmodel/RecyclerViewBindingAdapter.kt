package io.github.tturiyo.base.viewmodel

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import io.github.tturiyo.base.debug.Log

@BindingAdapter("items")
fun <T> setItems(recyclerView: RecyclerView, items: List<T>) {
    Log.d()

    val adapter = recyclerView.adapter

    assert(adapter is BaseRecyclerViewAdapter<*, *>)

    (adapter as BaseRecyclerViewAdapter<T, *>).setItems(items)
}