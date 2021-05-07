package com.example.dmeal.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.dmeal.R

fun ImageView.loadImage(image: String) {
    Glide.with(context).load(image)
        .error(R.drawable.ic_broken_image)
        .placeholder(R.drawable.ic_logo)
        .into(this)
}
