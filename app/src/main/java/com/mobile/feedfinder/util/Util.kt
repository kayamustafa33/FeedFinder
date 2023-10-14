package com.mobile.feedfinder.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.mobile.feedfinder.R

fun ImageView.downloadFromUrl(url : String?){
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.glide_loading_image)
        .error(R.drawable.no_internet)
        .fitCenter()
        .into(this)
}

fun Context.goActivityWithFlags(context: Context,targetActivity: Class<out Activity>){
    val intent = Intent(context,targetActivity)
    val activity = context as Activity
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
    context.startActivity(intent)
    activity.finish()
}