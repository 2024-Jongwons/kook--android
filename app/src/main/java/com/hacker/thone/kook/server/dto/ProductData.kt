package com.hacker.thone.kook.server.dto

import com.hacker.thone.kook.R

data class ProductData(
    val name : String? = "",
    val point : Int? = -1,
    val companyData: CompanyData? = CompanyData(),
    val image : Int? = R.drawable.logo
)
