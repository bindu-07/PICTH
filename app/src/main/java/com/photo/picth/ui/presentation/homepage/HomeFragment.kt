package com.photo.picth.ui.presentation.homepage

import CategoryAdapter
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.photo.picth.R
import com.photo.picth.adapter.BottomBannerAdapter
import com.photo.picth.adapter.CircleAdapter
import com.photo.picth.adapter.ImageSliderAdapter
import com.photo.picth.data.api.response.BaseResponse
import com.photo.picth.databinding.FragmentHomeBinding
import com.photo.picth.ui.presentation.homepage.viewmodel.HomeViewModel
import com.photo.picth.utils.ui.CommonMethod.Companion.showToast
import com.photo.picth.utils.ui.ProgressUtil.hideProgress
import com.photo.picth.utils.ui.ProgressUtil.showProgress


class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val handler = Handler(Looper.getMainLooper())
    private var currentPage = 0
    private lateinit var adapter: ImageSliderAdapter


    // Define the interval for auto-sliding (e.g., 3 seconds)
    private val slideInterval: Long = 3000

    // Define the runnable that switches pages
    private val autoSlideRunnable = object : Runnable {
        override fun run() {
            if (adapter.itemCount > 0) {
                currentPage = (currentPage + 1) % adapter.itemCount
                binding.viewPager.setCurrentItem(currentPage, true)
                handler.postDelayed(this, slideInterval)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val viewModel by viewModels<HomeViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHomeBinding.bind(view)
        viewModel.getHome()

        getData()
    }

    private fun getData() {
        viewModel.homeResult.observe(requireActivity()) {
            when (it) {
                is BaseResponse.Loading -> {
                    showProgress(requireActivity())
                }

                is BaseResponse.Success -> {

                    adapter = it.data?.let { it1 ->
                        ImageSliderAdapter(
                            requireActivity(), it1.data.slideBanner
                        )
                    }!!
                    binding.viewPager.adapter = adapter
                    // Start auto-slide
                    handler.postDelayed(autoSlideRunnable, slideInterval)

                    // Link the TabLayout with ViewPager2
                    TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, _ ->
                        binding.viewPager.setCurrentItem(tab.position, true)
                    }.attach()

                    binding.recyclerView.layoutManager =
                        LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)

                    binding.recyclerView.adapter = it.data?.let { it1 -> CategoryAdapter(it1.data) }

                    binding.recyclerViewAds.layoutManager = LinearLayoutManager(
                        requireActivity(), LinearLayoutManager.HORIZONTAL, false
                    )

                    binding.recyclerViewAds.adapter =
                        it.data?.let { it1 -> BottomBannerAdapter(it1.data.adsBanner) }
                    hideProgress()
                }

                is BaseResponse.Error -> {

                    it.msg?.let { it1 -> showToast(requireActivity(), it1) }
                }

                else -> {
                    hideProgress()
                }
            }
        }
    }


}