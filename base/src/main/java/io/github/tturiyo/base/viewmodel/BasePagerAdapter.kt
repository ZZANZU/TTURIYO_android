package io.github.tturiyo.base.viewmodel

import android.support.v4.view.PagerAdapter


abstract class BasePagerAdapter<in T> : PagerAdapter() {
    abstract fun setItems(items: List<T>)
}