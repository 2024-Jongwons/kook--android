package com.hacker.thone.kook.ui.adapter.reels

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.hacker.thone.kook.databinding.ItemReelsSmallBinding

class SmallReelsAdapter(private val reelsList: List<String>, val context: Context, val onMoveScreen : (data : String)->Unit) :
    RecyclerView.Adapter<SmallReelsAdapter.SmallReelsViewHolder>() {
    private lateinit var player: ExoPlayer
    inner class SmallReelsViewHolder(val binding: ItemReelsSmallBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(reelsData : String){
            val videoUrl = reelsData // 여기에 실제 동영상 URL을 입력하세요
            val mediaItem = MediaItem.fromUri(Uri.parse(videoUrl))
            player = ExoPlayer.Builder(context).build()
            player.setMediaItem(mediaItem)
            binding.videoView.player = player
            player.prepare()
            binding.videoView.setOnClickListener {
                onMoveScreen(reelsData)
                Log.d("test", "click videoView")
            }
            binding.heartButton.setOnClickListener {
                Log.d("test", "click heartButton")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmallReelsViewHolder {
        val binding =
            ItemReelsSmallBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return SmallReelsViewHolder(binding)
    }

    override fun getItemCount(): Int = reelsList.size


    override fun onBindViewHolder(holder: SmallReelsViewHolder, position: Int) {
        holder.bind(reelsData = reelsList[position])
    }
}