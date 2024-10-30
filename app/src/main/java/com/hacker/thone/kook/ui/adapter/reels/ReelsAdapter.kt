import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.hacker.thone.kook.databinding.ItemReelsBinding

class ReelsAdapter(
    private val reelsList: List<String>,
    val context: Context,
) : RecyclerView.Adapter<ReelsAdapter.RealsPagerViewHolder>() {

    private var exoPlayer: ExoPlayer? = null

    init {
        exoPlayer = ExoPlayer.Builder(context).build()
    }

    inner class RealsPagerViewHolder(val binding: ItemReelsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(reelsData: String) {
            var isPlayReels = false
            binding.heartButton.setOnClickListener {
                // Heart button 클릭 이벤트
            }

            binding.commentButton.setOnClickListener {
                // Comment button 클릭 이벤트
            }

            val mediaItem = MediaItem.fromUri(Uri.parse(reelsData))
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
                Log.d("test", "click videoView")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RealsPagerViewHolder {
        val binding = ItemReelsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RealsPagerViewHolder(binding)
    }

    override fun getItemCount(): Int = reelsList.size

    override fun onBindViewHolder(holder: RealsPagerViewHolder, position: Int) {
        Log.d("test", "in holder")
        holder.bind(reelsList[position])
    }

    override fun onViewRecycled(holder: RealsPagerViewHolder) {
        super.onViewRecycled(holder)
        exoPlayer?.stop()
    }

    fun releasePlayer() {
        exoPlayer?.release()
        exoPlayer = null
    }
}
