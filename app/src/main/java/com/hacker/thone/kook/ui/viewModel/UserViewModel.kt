package com.hacker.thone.kook.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    private val _userPoint = MutableLiveData<Int>().apply { value = 5000 }
    val userPoint : LiveData<Int> = _userPoint

    fun modifyUserPoint(modifyPoint : Int){
        _userPoint.value = modifyPoint
    }

}