package kz.nura.zapistestapp.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
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

@BindingAdapter("bind:priceText")
fun setPrice(textView: TextView, price: Int) {
    textView.text = price.toString().plus(" ").plus(getTengeSymbol())
}

@BindingAdapter("bind:address")
fun setAddress(textView: TextView, address: String?) {
    address?.let {
        var newAddress = address
        if (address.contains("<br />")) {
            newAddress = address.replace("<br />", "\n")
        }
        textView.text = newAddress
    }
}

@BindingAdapter("bind:goneIfNull")
fun goneIfNull(view: View, it: Any?) {
    view.visibility = if (it != null) View.VISIBLE else View.GONE
}