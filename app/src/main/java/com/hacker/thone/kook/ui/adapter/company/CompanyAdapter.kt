package com.hacker.thone.kook.ui.adapter.company

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hacker.thone.kook.R
import com.hacker.thone.kook.databinding.ItmeCompanyTagBinding
import com.hacker.thone.kook.server.dto.CompanyData

class CompanyAdapter(private val companyList: List<CompanyData>, val selectIndex : Int, val changePos : (position : Int) -> Unit) :
    RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder>() {
    inner class CompanyViewHolder(val binding: ItmeCompanyTagBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CompanyData, position: Int) {
            binding.companyNameText.text = data.name
            binding.companyImage.setImageResource(data.image ?: R.drawable.logo)
            if (selectIndex == position) {
                binding.background.setBackgroundResource(R.drawable.sel_company_company_true)
            }
            else{
                binding.background.setBackgroundResource(R.drawable.sel_company_company_false)
            }
            binding.root.setOnClickListener {
                changePos(position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val binding = ItmeCompanyTagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CompanyViewHolder(binding)
    }

    override fun getItemCount(): Int = companyList.size
    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.bind(companyList[position], position)
    }
}