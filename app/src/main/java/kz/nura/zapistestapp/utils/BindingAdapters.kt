package kz.nura.zapistestapp.utils

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import kz.nura.zapistestapp.network.DOMAIN
import java.lang.Exception

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

@BindingAdapter("bind:showIfNull")
fun showIfNull(view: View, it: Any?) {
    if (it == null) {
        Log.d("### salon", "null")
    } else {
        Log.d("### salon", "$it")
    }
    view.visibility = if (it != null) View.GONE else View.VISIBLE
}

@BindingAdapter("bind:goneIfNull")
fun goneIfNull(view: View, it: Any?) {
    if (it == null) {
        Log.d("### error", "null")
    } else {
        Log.d("### error", "${(it as Exception).message}")
    }
    view.visibility = if (it != null) View.VISIBLE else View.GONE
}

@BindingAdapter(value = ["bind:salons", "bind:exception"])
fun setProgressBar(view: View, it: Any?, exception: Exception?) {
    Log.d("###", "salon: $it, ex: $exception")
    if(it == null && exception == null) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}


//@BindingAdapter("bind:goneIfNull")
//fun goneIfNull(view: View, it: Any?) {
//    if (it == null) {
//        Log.d("### error", "null")
//    } else {
//        Log.d("### error", "${(it as Exception).message}")
//    }
//    view.visibility = if (it != null) View.VISIBLE else View.GONE
//}