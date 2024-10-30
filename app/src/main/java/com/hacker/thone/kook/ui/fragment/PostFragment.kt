package com.hacker.thone.kook.ui.fragment

import ReelsAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.hacker.thone.kook.R
import com.hacker.thone.kook.databinding.FragmentPostBinding
import com.hacker.thone.kook.ui.viewModel.PostViewModel

class PostFragment : Fragment() {

    private var _binding : FragmentPostBinding? = null
    private val binding get() = _binding!!
    private val postViewModel by activityViewModels<PostViewModel>()
    private var reelsAdapter: ReelsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostBinding.inflate(inflater, container, false)

        postViewModel.reelsList.observe(viewLifecycleOwner){
            initViewPagerAdapter(it)
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initViewPagerAdapter(reelsList : List<String>){
        Log.d("test", "in initViewPagerAdapter")
        reelsAdapter?.notifyItemRemoved(0)
        reelsAdapter = ReelsAdapter(
            context = requireContext(),
            reelsList = reelsList
        )
//        {
//
//        }

        with(binding) {
            reelsViewPager.adapter = reelsAdapter
            reelsViewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
        }

    }
}