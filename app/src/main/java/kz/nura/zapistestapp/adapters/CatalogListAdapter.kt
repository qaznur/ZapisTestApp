package kz.nura.zapistestapp.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kz.nura.zapistestapp.R
import kz.nura.zapistestapp.databinding.ListitemCatalogBinding
import kz.nura.zapistestapp.domain.Salon
import kz.nura.zapistestapp.ui.DetailsActivity

class CatalogListAdapter(private val clickListener: ClickListener) :
    ListAdapter<Salon, CatalogListAdapter.SalonViewHolder>(SalonDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SalonViewHolder {
        return SalonViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SalonViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }


    class SalonViewHolder(private val binding: ListitemCatalogBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(salon: Salon, clickListener: ClickListener) {
            binding.salon = salon
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): SalonViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding: ListitemCatalogBinding =
                    DataBindingUtil.inflate(inflater, R.layout.listitem_catalog, parent, false)
                return SalonViewHolder(binding)
            }
        }
    }

    class SalonDiffUtil : DiffUtil.ItemCallback<Salon>() {
        override fun areItemsTheSame(oldItem: Salon, newItem: Salon): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Salon, newItem: Salon): Boolean {
            return oldItem == newItem
        }
    }

    interface ClickListener {
        fun onClicked(salon: Salon)
    }

    class ClickListenerImpl(private val context: Context?) : ClickListener {

        override fun onClicked(salon: Salon) {
            context?.let {
                Log.d("#####", "ClickListenerImpl id: ${salon.id}")
                val intent = Intent(context, DetailsActivity::class.java)
                intent.putExtra("id", salon.id)
                context.startActivity(intent)
            }
        }

    }
}