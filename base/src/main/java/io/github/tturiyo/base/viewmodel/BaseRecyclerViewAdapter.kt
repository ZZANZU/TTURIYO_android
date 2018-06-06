package io.github.tturiyo.base.viewmodel

import android.support.v7.widget.RecyclerView


abstract class BaseRecyclerViewAdapter<in T, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {
    abstract fun setItems(items: List<T>)
}