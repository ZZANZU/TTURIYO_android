package io.github.tturiyo.base.viewmodel


import android.databinding.BindingAdapter
import android.widget.EditText
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.base.utils.toDateTimeFormat
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("android:text")
fun setValue(editText: EditText, value: Long) {
    Log.d()

    editText.setText(value.toDateTimeFormat("yyyy-MM-dd HH:mm"))
}
