package com.rootstrap.donations.utils

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import com.rootstrap.donations.R

class CustomTextInputLayout : TextInputLayout {
    constructor(context: Context) : super(context) {
        fixPadding()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        fixPadding()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        fixPadding()
    }

    private fun fixPadding() {
        val errorTV = findViewById<TextView>(R.id.textinput_error)
        errorTV.setPadding(errorTV.paddingBottom, errorTV.paddingTop + 5, errorTV.paddingRight, errorTV.paddingLeft)
    }
}