package kz.nura.zapistestapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kz.nura.zapistestapp.R
import kz.nura.zapistestapp.adapters.PagerAdapter
import kz.nura.zapistestapp.databinding.ActivityDetailsBinding
import kz.nura.zapistestapp.viewmodels.DetailsViewModel

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var viewModel: DetailsViewModel
    private lateinit var factory: DetailsViewModel.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        binding.lifecycleOwner = this

        val salonId = intent.extras?.getLong("id")
        factory = DetailsViewModel.Factory(salonId)

        val pagerAdapter = PagerAdapter(
            this, listOf(), true
        )

        viewModel = ViewModelProvider(this, factory).get(DetailsViewModel::class.java)
        viewModel.imageUrls.observe(this, Observer { imageUrls ->
            Log.d("#####", "imageUrls: $imageUrls")
            pagerAdapter.setItemList(imageUrls)
            binding.viewpager.reset()
        })
        binding.viewModel = viewModel
        binding.viewpager.adapter = pagerAdapter
    }

    override fun onResume() {
        super.onResume()
        binding.viewpager.resumeAutoScroll()
    }

    override fun onPause() {
        super.onPause()
        binding.viewpager.pauseAutoScroll()
    }
}
