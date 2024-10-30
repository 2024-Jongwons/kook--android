package com.hacker.thone.kook.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hacker.thone.kook.R
import com.hacker.thone.kook.databinding.FragmentShoppingBinding
import com.hacker.thone.kook.server.dto.CompanyData
import com.hacker.thone.kook.server.dto.ProductData
import com.hacker.thone.kook.ui.adapter.company.CompanyAdapter
import com.hacker.thone.kook.ui.adapter.product.HorizonProductAdapter
import com.hacker.thone.kook.ui.adapter.reels.SmallReelsAdapter
import com.hacker.thone.kook.ui.decoration.reels.SmallReelsDecoration
import com.hacker.thone.kook.ui.viewModel.MainViewModel
import com.hacker.thone.kook.ui.viewModel.ShoppingViewModel
import com.hacker.thone.kook.ui.viewModel.UserViewModel
import com.hacker.thone.kook.util.formatNumberWithComma

class ShoppingFragment : Fragment() {

    companion object{
        val companyList = listOf<CompanyData>(
                CompanyData("Walmart", R.drawable.company0),
                CompanyData("7-ELEVEN", R.drawable.company1),
                CompanyData("Kroger", R.drawable.company2),
                CompanyData("target", R.drawable.company3),
                CompanyData("Costco", R.drawable.company4),
                CompanyData("amazon", R.drawable.company5),
            CompanyData("IKEA", R.drawable.company6),
        )
    }

    private var _binding : FragmentShoppingBinding? = null
    private val binding get() = _binding!!
    private var selectCompanyIndex : Int = 0

    private val shoppingViewModel by activityViewModels<ShoppingViewModel>()
    private val userViewModel by activityViewModels<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoppingBinding.inflate(inflater, container, false)

        userViewModel.userPoint.observe(viewLifecycleOwner){
            binding.voucherPoint.text = formatNumberWithComma(it)
        }

        initCompanyRecyclerView()
        return binding.root
    }

    private fun initCompanyRecyclerView() {
        val productAdapter = HorizonProductAdapter(
            listOf(ProductData(name = "somting", point = 3500, image = R.drawable.company4, companyData = companyList[selectCompanyIndex])),
        ){
            shoppingViewModel.setSelectProductInfo(it)
            findNavController().navigate(R.id.action_shoppingFragment_to_shoppingProductFragment)
        }
        with(binding) {
            productRecyclerView.adapter = productAdapter
            productRecyclerView.scrollToPosition(selectCompanyIndex-1)
        }
        val companyAdapter = CompanyAdapter(
            companyList =  companyList,
            selectIndex = selectCompanyIndex,
        ){ pos ->
            selectCompanyIndex = pos
            initCompanyRecyclerView()
        }
        binding.companyTitle.text = companyList[selectCompanyIndex].name
        with(binding) {
            companyRecyclerView.adapter = companyAdapter
            companyRecyclerView.scrollToPosition(selectCompanyIndex-1)
            if (companyRecyclerView.itemDecorationCount == 0){
                companyRecyclerView.addItemDecoration(SmallReelsDecoration(companyList.size))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}