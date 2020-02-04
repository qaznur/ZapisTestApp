package kz.nura.zapistestapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kz.nura.zapistestapp.R
import kz.nura.zapistestapp.databinding.ListitemCatalogBinding
import kz.nura.zapistestapp.domain.Salon

class CatalogListAdapter : ListAdapter<Salon, CatalogListAdapter.SalonViewHolder>(SalonDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SalonViewHolder {
        return SalonViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SalonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class SalonViewHolder(private val binding: ListitemCatalogBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(salon: Salon) {
            binding.salon = salon
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
}