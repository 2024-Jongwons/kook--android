package com.hacker.thone.kook.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hacker.thone.kook.R
import com.hacker.thone.kook.server.dto.CompanyData
import com.hacker.thone.kook.server.dto.ProductData

class ShoppingViewModel : ViewModel() {
    private val _selectProduct =
        MutableLiveData<ProductData>().apply { value = ProductData( name ="",  point =-1,  image = 0, companyData =  CompanyData()) }
    val selectProduct: LiveData<ProductData> = _selectProduct

    private val _buyVoucherList = MutableLiveData<List<ProductData>>().apply { value = emptyList() }
    val buyVoucherList: LiveData<List<ProductData>> = _buyVoucherList


    fun setSelectProductInfo(setProductData: ProductData) {
        _selectProduct.value = setProductData
    }

    fun addBuyVoucherList(setProductData: ProductData) {
        val list = _buyVoucherList.value?.toMutableList() ?: mutableListOf()
        list.add(setProductData)
        _buyVoucherList.value = list
    }
}