package com.hacker.thone.kook.ui.viewModel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PostViewModel : ViewModel() {
    private val _reelsList = MutableLiveData<List<String>>().apply { value = emptyList() }
    val reelsList: LiveData<List<String>> = _reelsList

    private val _reelsUri = MutableLiveData<Uri>().apply { value = Uri.parse("") }
    val reelsUri: LiveData<Uri> = _reelsUri

    fun setReelsList(setReelsList: List<String>) {
        _reelsList.value = setReelsList
    }

    private val _selectId = MutableLiveData<Int>().apply { value = -1 }
    val selectId: LiveData<Int> = _selectId

    fun setReelsList(setId: Int) {
        _selectId.value = setId
    }

    fun setUri(setUri: Uri) {
        _reelsUri.value = setUri
    }
}