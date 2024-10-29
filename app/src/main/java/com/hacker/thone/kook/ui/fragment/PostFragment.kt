package com.hacker.thone.kook.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hacker.thone.kook.R
import com.hacker.thone.kook.databinding.FragmentPostBinding

class PostFragment : Fragment() {

    private var _binding : FragmentPostBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostBinding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}