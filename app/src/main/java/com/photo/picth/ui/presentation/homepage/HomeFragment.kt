package com.photo.picth.ui.presentation.homepage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.photo.picth.R
import com.photo.picth.databinding.FragmentHomeBinding
import com.photo.picth.adapter.BannerAdapter


 class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun callFrom(): String = "HOME"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHomeBinding.bind(view)

        initUI()
//        initObservers()
//        findNavController().clearBackStack(R.id.homeFragment)
//        checkPermission()
    }






    private fun initUI() {
        _binding!!.rvStories.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        _binding!!.rvStories.adapter = BannerAdapter(R.layout.rvstories_item)

        _binding!!.rvBanner2.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        _binding!!.rvBanner2.adapter = BannerAdapter(R.layout.rv_moti_item)

        _binding!!.rvBanner3.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        _binding!!.rvBanner3.adapter = BannerAdapter(R.layout.rv3_item)

        _binding!!.rvBanner4.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        _binding!!.rvBanner4.adapter = BannerAdapter(R.layout.rv4_item)

        _binding!!.rvFestival.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        _binding!!.rvFestival.adapter = BannerAdapter(R.layout.rvfestival_item)

        _binding!!.rvRank.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        _binding!!.rvRank.adapter = BannerAdapter(R.layout.rvfestival_item)

        _binding!!.rvAchievements.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        _binding!!.rvAchievements.adapter = BannerAdapter(R.layout.rvfestival_item)

        _binding!!.rvBonanza.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        _binding!!.rvBonanza.adapter = BannerAdapter(R.layout.rvfestival_item)

        _binding!!.rvAnniversary.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        _binding!!.rvAnniversary.adapter = BannerAdapter(R.layout.rvfestival_item)

        _binding!!.rvMsg.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        _binding!!.rvMsg.adapter = BannerAdapter(R.layout.rvfestival_item)

        _binding!!.rvMeeting.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        _binding!!.rvMeeting.adapter = BannerAdapter(R.layout.rvfestival_item)

        _binding!!.rvIncome.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        _binding!!.rvIncome.adapter = BannerAdapter(R.layout.rvfestival_item)

        _binding!!.rvCapping.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        _binding!!.rvCapping.adapter = BannerAdapter(R.layout.rv_item)


    }


}