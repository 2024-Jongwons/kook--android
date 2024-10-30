package com.hacker.thone.kook.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hacker.thone.kook.R
import com.hacker.thone.kook.databinding.FragmentVoucherBinding
import com.hacker.thone.kook.ui.viewModel.ShoppingViewModel

class VoucherFragment : Fragment() {

    private var _binding: FragmentVoucherBinding? = null
    private val binding get() = _binding!!
    private val shoppingViewModel by activityViewModels<ShoppingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVoucherBinding.inflate(inflater, container, false)

        shoppingViewModel.selectProduct.observe(viewLifecycleOwner){
            binding.voucherImage.setImageResource(it.image ?: R.drawable.logo   )
            binding.voucherPoint.text = it.point.toString()
            binding.companyText
        }

        binding.backArrowImage.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        return binding.root
    }
}