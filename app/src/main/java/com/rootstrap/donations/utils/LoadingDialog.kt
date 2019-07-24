package com.rootstrap.donations.utils

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.rootstrap.donations.R

class LoadingDialog(context: Context, cancelListener: DialogInterface.OnCancelListener?) : Dialog(context) {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_loading)

        if (window != null) {
            window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        setCanceledOnTouchOutside(false)
        if (cancelListener != null) {
            setCancelable(true)
            setOnCancelListener(cancelListener)
        } else {
            setCancelable(false)
        }
    }
}
