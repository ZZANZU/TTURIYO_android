package io.github.tturiyo.base.viewmodel

import android.databinding.BindingAdapter
import android.support.v4.view.ViewPager
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.base.debug.assertDebug

@BindingAdapter("items")
fun <T> setItems(viewPager: ViewPager, items: List<T>) {
    Log.d()

    val adapter = viewPager.adapter

    assertDebug(adapter is BasePagerAdapter<*>)

    (adapter as BasePagerAdapter<T>).setItems(items)
}