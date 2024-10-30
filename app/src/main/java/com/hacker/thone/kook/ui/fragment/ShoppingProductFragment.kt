package com.hacker.thone.kook.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.hacker.thone.kook.R
import com.hacker.thone.kook.databinding.FragmentShoppingProductBinding
import com.hacker.thone.kook.ui.viewModel.ShoppingViewModel
import com.hacker.thone.kook.util.formatNumberWithComma

class ShoppingProductFragment : Fragment() {

    private var _binding : FragmentShoppingProductBinding? = null
    private val binding get() = _binding!!
    private val shoppingViewModel by activityViewModels<ShoppingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoppingProductBinding.inflate(inflater, container, false)

        binding.backArrowImage.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        shoppingViewModel.selectProduct.observe(viewLifecycleOwner){
            Log.d("test", "it : $it")
            binding.voucherName.text = "[${it.companyData?.name}] ${it.name}"
            binding.productImage.setImageResource(it.image ?: R.drawable.logo   )
            binding.voucherPoint.text = formatNumberWithComma(it.point ?: 0)
            binding.companyText.text = it.companyData?.name
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}