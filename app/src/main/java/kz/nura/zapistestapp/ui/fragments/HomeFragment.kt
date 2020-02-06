package kz.nura.zapistestapp.ui.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kz.nura.zapistestapp.NoInternetException

import kz.nura.zapistestapp.R
import kz.nura.zapistestapp.ServerException
import kz.nura.zapistestapp.adapters.CatalogListAdapter
import kz.nura.zapistestapp.databinding.FragmentHomeBinding
import kz.nura.zapistestapp.viewmodels.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: CatalogListAdapter
    private lateinit var factory: HomeViewModel.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        adapter = CatalogListAdapter(CatalogListAdapter.ClickListenerImpl(context))
        binding.catalogList.adapter = adapter
        binding.catalogList.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        factory = HomeViewModel.Factory(activity!!.application)
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
        binding.viewModel = viewModel
        setViewModelObserves()
    }

    private fun setViewModelObserves() {
        viewModel.salons.observe(viewLifecycleOwner, Observer { salons ->
            if (salons == null) {
                binding.loadProgressBar.visibility = View.VISIBLE
                binding.loadErrorLayout.visibility = View.GONE
            } else {
                binding.loadErrorLayout.visibility = View.GONE
                binding.loadProgressBar.visibility = View.GONE
                viewModel.onSetNoException()
            }
            adapter.submitList(salons)
        })

        viewModel.exception.observe(viewLifecycleOwner, Observer { ex ->
            if (ex == null) {
                binding.loadErrorLayout.visibility = View.GONE
            } else {
                when (ex) {
                    is NoInternetException -> {
                        binding.errorMessage.text = getString(R.string.no_internet_error)
                        binding.refresh.visibility = View.VISIBLE
                    }
                    is ServerException -> {
                        binding.errorMessage.text = getString(R.string.server_error)
                        binding.refresh.visibility = View.GONE
                    }
                    is UnknownError -> {
                        binding.errorMessage.text = getString(R.string.unknown_error)
                        binding.refresh.visibility = View.GONE
                    }
                }
                binding.loadProgressBar.visibility = View.GONE
                binding.loadErrorLayout.visibility = View.VISIBLE
            }
        })
    }

}
