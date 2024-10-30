package com.hacker.thone.kook.ui.adapter.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hacker.thone.kook.R
import com.hacker.thone.kook.databinding.ItemProductBinding
import com.hacker.thone.kook.server.dto.CompanyData
import com.hacker.thone.kook.server.dto.ProductData
import com.hacker.thone.kook.util.formatNumberWithComma

class HorizonProductAdapter(private val productList: List<ProductData>, val onMoveScreen : (ProductData) -> Unit) :
    RecyclerView.Adapter<HorizonProductAdapter.HorizonProductViewHolder>() {
    inner class HorizonProductViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ProductData) {
            val companyName = data.companyData?.name
            binding.companyText.text = companyName
            binding.voucherImage.setImageResource(data.image ?: R.drawable.logo)
            binding.voucherName.text = "[$companyName] ${data.name}"
            binding.voucherPoint.text = formatNumberWithComma(data.point ?: 0)
            binding.root.setOnClickListener {
                onMoveScreen(data)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizonProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HorizonProductViewHolder(binding)
    }

    override fun getItemCount(): Int = productList.size
    override fun onBindViewHolder(holder: HorizonProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }
}