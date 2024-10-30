package com.hacker.thone.kook.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PostViewModel : ViewModel() {
    private val _reelsList = MutableLiveData<List<String>>().apply { value = emptyList() }
    val reelsList: LiveData<List<String>> = _reelsList

    fun setReelsList(setReelsList: List<String>) {
        _reelsList.value = setReelsList
    }

    private val _selectId = MutableLiveData<Int>().apply { value = -1 }
    val selectId: LiveData<Int> = _selectId

    fun setReelsList(setId: Int) {
        _selectId.value = setId
    }
}