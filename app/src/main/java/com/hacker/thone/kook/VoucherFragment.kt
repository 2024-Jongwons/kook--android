package com.hacker.thone.kook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hacker.thone.kook.databinding.FragmentVoucherBinding

class VoucherFragment : Fragment() {

    private var _binding: FragmentVoucherBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVoucherBinding.inflate(inflater, container, false)
        return binding.root
    }
}