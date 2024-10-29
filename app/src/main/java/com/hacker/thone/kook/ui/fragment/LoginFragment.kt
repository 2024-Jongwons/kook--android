package com.hacker.thone.kook.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hacker.thone.kook.databinding.FragmentLoginBinding
import com.hacker.thone.kook.ui.activite.HomeActivity

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.nextButton.setOnClickListener {
            moveHomeActivity()
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun moveHomeActivity(){
        startActivity(Intent(requireContext(), HomeActivity::class.java))
        requireActivity().finish()
    }
}