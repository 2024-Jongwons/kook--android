package com.hacker.thone.kook.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.hacker.thone.kook.R
import com.hacker.thone.kook.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        initProfileView()

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initProfileView(){
        val tabLayout = binding.tabLayout

        val homeTab = tabLayout.newTab()
        homeTab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_check)
        tabLayout.addTab(homeTab)

        val profileTab = tabLayout.newTab()
        profileTab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_card)
        tabLayout.addTab(profileTab)
    }
}