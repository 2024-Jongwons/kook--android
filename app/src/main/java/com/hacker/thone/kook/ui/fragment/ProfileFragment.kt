package com.hacker.thone.kook.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.hacker.thone.kook.R
import com.hacker.thone.kook.databinding.FragmentProfileBinding
import com.hacker.thone.kook.ui.viewModel.MainViewModel
import com.hacker.thone.kook.ui.viewModel.UserViewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val userViewModel by activityViewModels<UserViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        initProfileView()
        userViewModel.userPoint.observe(viewLifecycleOwner){
            binding.voucherPointText.text = it.toString()
        }

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