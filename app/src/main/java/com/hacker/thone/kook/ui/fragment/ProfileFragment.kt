package com.hacker.thone.kook.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.hacker.thone.kook.R
import com.hacker.thone.kook.databinding.FragmentProfileBinding
import com.hacker.thone.kook.server.dto.ProductData
import com.hacker.thone.kook.ui.adapter.product.HorizonProductAdapter
import com.hacker.thone.kook.ui.fragment.ShoppingFragment.Companion.companyList
import com.hacker.thone.kook.ui.viewModel.ShoppingViewModel
import com.hacker.thone.kook.ui.viewModel.UserViewModel
import com.hacker.thone.kook.util.formatNumberWithComma

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val userViewModel by activityViewModels<UserViewModel>()
    private val shoppingViewModel by activityViewModels<ShoppingViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        initProfileView()
        userViewModel.userPoint.observe(viewLifecycleOwner){
            binding.voucherPointText.text = formatNumberWithComma(it)
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position) {
                    0 -> {
                        // TODO
                    }
                    1 -> {
                        initVoucherRecyclerView()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        return binding.root
    }

    private fun initVoucherRecyclerView() {
        val productAdapter = HorizonProductAdapter(
            listOf(ProductData(name = "somting", point = 3500, image = R.drawable.company4, companyData = companyList[0])),
        ){
            shoppingViewModel.setSelectProductInfo(it)
            findNavController().navigate(R.id.action_profileFragment_to_voucherFragment)
        }
        with(binding) {
            postRecyclerView.adapter = productAdapter
        }
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