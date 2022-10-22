package com.sdk.handleraysc.ext

import android.content.Context
import android.view.View
import android.widget.Toast

fun View.click(view: (View) -> Unit) {
    this.setOnClickListener {
        view(it)
    }
}
fun Context.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}