package com.sunasterisk.dmealfoodapp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.sunasterisk.dmealfoodapp.R

fun ImageView.loadImage(image: String) {
    Glide.with(context).load(image)
        .error(R.drawable.ic_broken_image)
        .placeholder(R.drawable.ic_logo)
        .into(this)
}
