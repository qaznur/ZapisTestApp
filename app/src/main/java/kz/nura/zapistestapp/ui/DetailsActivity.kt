package kz.nura.zapistestapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_details.view.*
import kz.nura.zapistestapp.R
import kz.nura.zapistestapp.adapters.PagerAdapter
import kz.nura.zapistestapp.adapters.ServiceListAdapter
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
            this, listOf(), false
        )
        val serviceAdapter = ServiceListAdapter()

        viewModel = ViewModelProvider(this, factory).get(DetailsViewModel::class.java)
        viewModel.imageUrls.observe(this, Observer { imageUrls ->
            Log.d("#####", "imageUrls: $imageUrls")
            pagerAdapter.setItemList(imageUrls)
            binding.pageIndicatorView.count = imageUrls.size
            binding.viewpager.reset()
        })
        viewModel.salonDetail.observe(this, Observer { salonDetail ->
            serviceAdapter.submitList(salonDetail.services)
        })
        binding.viewModel = viewModel
        val linearLayout = LinearLayoutManager(this)
        binding.viewpager.adapter = pagerAdapter
        binding.serviceList.adapter = serviceAdapter
        binding.serviceList.layoutManager = linearLayout
        binding.viewpager.addOnPageChangeListener(viewPagerListener)
    }

    private val viewPagerListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            binding.pageIndicatorView.setSelected(position)
        }
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
