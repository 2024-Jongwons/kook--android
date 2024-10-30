package com.hacker.thone.kook

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.hacker.thone.kook.databinding.FragmentEditVideoBinding
import com.hacker.thone.kook.ui.viewModel.PostViewModel

class EditVideoFragment : Fragment() {

    private var _binding : FragmentEditVideoBinding? = null
    private val binding get() = _binding!!
    private val postViewModel by activityViewModels<PostViewModel>()

    private var exoPlayer: ExoPlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exoPlayer = ExoPlayer.Builder(requireContext()).build().apply {
            repeatMode = ExoPlayer.REPEAT_MODE_ONE
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var isPlayReels = false
        _binding = FragmentEditVideoBinding.inflate(inflater, container, false)
        postViewModel.reelsUri.observe(viewLifecycleOwner){
            val mediaItem = MediaItem.fromUri(it)
            exoPlayer?.setMediaItem(mediaItem)
            binding.videoView.player = exoPlayer
            exoPlayer?.prepare()
            exoPlayer?.playWhenReady = true

            binding.videoView.setOnClickListener {
                if (isPlayReels) {
                    exoPlayer?.play()
                    isPlayReels = false
                } else {
                    exoPlayer?.pause()
                    isPlayReels = true
                }
            }
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}