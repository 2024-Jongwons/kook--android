package com.hacker.thone.kook.ui.decoration.reels

import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SmallReelsDecoration  (private val lastIndex : Int ) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        Log.d( "pos", "pos : $position")
        if (position == 0){
            outRect.left = 40
            outRect.right = 20
        }
        else if (position == lastIndex-1){
            outRect.right = 40
        }
        else{
            outRect.right = 20
        }

    }
}