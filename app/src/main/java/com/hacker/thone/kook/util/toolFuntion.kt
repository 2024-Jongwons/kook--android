package com.hacker.thone.kook.util

import java.text.DecimalFormat

fun formatNumberWithComma(number: Int): String {
    val decimalFormat = DecimalFormat("#,###")
    return decimalFormat.format(number)
}