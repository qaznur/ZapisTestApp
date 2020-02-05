package kz.nura.zapistestapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kz.nura.zapistestapp.R
import kz.nura.zapistestapp.databinding.ListitemServiceBinding
import kz.nura.zapistestapp.domain.SalonService

class ServiceListAdapter :
    ListAdapter<SalonService, ServiceListAdapter.ServiceViewHolder>(ServiceDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        return ServiceViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class ServiceViewHolder(private val binding: ListitemServiceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(service: SalonService) {
            binding.service = service
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ServiceViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding: ListitemServiceBinding =
                    DataBindingUtil.inflate(inflater, R.layout.listitem_service, parent, false)
                return ServiceViewHolder(binding)
            }
        }
    }

    class ServiceDiffUtil : DiffUtil.ItemCallback<SalonService>() {
        override fun areItemsTheSame(oldItem: SalonService, newItem: SalonService): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SalonService, newItem: SalonService): Boolean {
            return oldItem == newItem
        }
    }

}