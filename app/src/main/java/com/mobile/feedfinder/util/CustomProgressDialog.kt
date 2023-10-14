package com.mobile.feedfinder.util

import android.app.Dialog
import android.content.Context
import com.mobile.feedfinder.R

class CustomProgressDialog (val context: Context) {

    private var progressDialog =  Dialog(context)

    fun show() {
        progressDialog.setContentView(R.layout.full_screen_progress_bar)
        progressDialog.setCancelable(false)
        progressDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        progressDialog.show()
    }

    fun dismiss() {
        progressDialog.dismiss()
    }

}