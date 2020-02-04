package kz.nura.zapistestapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import kz.nura.zapistestapp.network.DOMAIN

@BindingAdapter("bind:imageUrl")
fun loadImage(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(imageView.context)
            .load(DOMAIN.plus(imageUrl))
            .into(imageView)
    }
}