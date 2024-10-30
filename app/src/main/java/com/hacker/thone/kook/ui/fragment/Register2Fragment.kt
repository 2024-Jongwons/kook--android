package com.hacker.thone.kook.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hacker.thone.kook.databinding.FragmentRegister2Binding


class Register2Fragment : Fragment() {
    private val binding get() = _binding!!
    private var _binding: FragmentRegister2Binding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegister2Binding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}