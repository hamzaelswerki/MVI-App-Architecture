package com.enghamza.mviapp.utils

import android.view.View
import android.widget.Adapter
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.enghamza.mviapp.R
import com.enghamza.mviapp.databinding.ItemMovieRecyclerBinding
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory


object HolderAdapter {

    @JvmStatic
    @BindingAdapter("loadImage")
      fun loadImage(view: ImageView,imgUrl: String){
    Glide.with(view)
        .load(StringConstants.imageUrl + imgUrl)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(view)
    }




}