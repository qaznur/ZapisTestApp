package kz.nura.zapistestapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.asksira.loopingviewpager.LoopingPagerAdapter
import com.bumptech.glide.Glide
import kz.nura.zapistestapp.R
import kz.nura.zapistestapp.network.DOMAIN

class PagerAdapter(context: Context, dataSet: List<String>, isInfinite: Boolean) :
    LoopingPagerAdapter<String>(context, dataSet, isInfinite) {

    override fun inflateView(viewType: Int, container: ViewGroup?, listPosition: Int): View {
        return LayoutInflater.from(context).inflate(R.layout.item_image_pager, container, false)
    }

    override fun bindView(convertView: View?, listPosition: Int, viewType: Int) {
        convertView?.let {
            val image = it.findViewById<ImageView>(R.id.salon_image)
            val item = getItem(listPosition)
            Glide.with(context)
                .load(DOMAIN.plus(item))
                .into(image)
        }
    }
}