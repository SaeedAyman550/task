package com.example.test.presentation.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


fun loadImageWithShimmer(
    imageUri:String,
    image:ImageView,
    shimmer:View,
    radius:Int=1
){
    Glide.with(image.context)
        .load(imageUri)
        .transform(RoundedCorners(radius))
        .listener(object : RequestListener<Drawable> {
            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: com.bumptech.glide.load.DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                shimmer.visibility = View.GONE
                return false
            }

            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }
        })
        .into(image)
}

fun loadGlideImage(
    imageUri:String,
    image:ImageView,
    radius:Int=1
){
    Glide.with(image.context)
        .load(imageUri)
        .transform(RoundedCorners(radius))
        .into(image)
}