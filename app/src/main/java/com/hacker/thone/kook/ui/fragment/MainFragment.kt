package com.hacker.thone.kook.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hacker.thone.kook.R
import com.hacker.thone.kook.databinding.FragmentMainBinding
import com.hacker.thone.kook.ui.adapter.reels.SmallReelsAdapter
import com.hacker.thone.kook.ui.decoration.reels.SmallReelsDecoration
import com.hacker.thone.kook.ui.viewModel.MainViewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel by activityViewModels<MainViewModel>()
    private val testList = listOf(
        "https://khtback.s3.ap-northeast-2.amazonaws.com/f91a8928-4669-42e4-9d73-81f76c5012d6.mov",
        "https://khtback.s3.ap-northeast-2.amazonaws.com/f91a8928-4669-42e4-9d73-81f76c5012d6.mov",
        "https://khtback.s3.ap-northeast-2.amazonaws.com/f91a8928-4669-42e4-9d73-81f76c5012d6.mov",
        "https://khtback.s3.ap-northeast-2.amazonaws.com/f91a8928-4669-42e4-9d73-81f76c5012d6.mov",
        "https://khtback.s3.ap-northeast-2.amazonaws.com/f91a8928-4669-42e4-9d73-81f76c5012d6.mov",
        "https://khtback.s3.ap-northeast-2.amazonaws.com/f91a8928-4669-42e4-9d73-81f76c5012d6.mov",
        "https://khtback.s3.ap-northeast-2.amazonaws.com/f91a8928-4669-42e4-9d73-81f76c5012d6.mov",
        "https://khtback.s3.ap-northeast-2.amazonaws.com/f91a8928-4669-42e4-9d73-81f76c5012d6.mov"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        initRecyclerView()
        // Inflate the layout for this fragment
        binding.profileButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_profileFragment)
        }
        return binding.root
    }

    private fun initRecyclerView() {
        val smallReelsAdapter = SmallReelsAdapter(
            reelsList =  testList,
            context = requireContext()
        ){

        }
        smallReelsAdapter.notifyItemRemoved(0)

        with(binding) {
            buldakRecyclerView.adapter = smallReelsAdapter
            upArrowRecyclerView.adapter = smallReelsAdapter
            if (buldakRecyclerView.itemDecorationCount == 0){
                buldakRecyclerView.addItemDecoration(SmallReelsDecoration(testList.size))
            }
            if (upArrowRecyclerView.itemDecorationCount == 0){
                upArrowRecyclerView.addItemDecoration(SmallReelsDecoration(testList.size))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}