package com.mobile.feedfinder.util

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