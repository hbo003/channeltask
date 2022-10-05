package com.example.myapplicationtask.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplicationtask.databinding.ItemLogoBinding
import com.example.myapplicationtask.model.LogoItem
import com.example.myapplicationtask.utils.BaseViewHolder


class LogoAdapter(
    private val items: List<LogoItem>, private val context: Context

) : RecyclerView.Adapter<BaseViewHolder<LogoItem>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogoViewHolder {
        val binding = ItemLogoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LogoViewHolder(binding = binding, context = context)
    }


    class LogoViewHolder(var binding: ItemLogoBinding, val context: Context) :
        BaseViewHolder<LogoItem>(binding.root) {
        override fun bind(data: LogoItem) {
            binding.titleProductLogo.text = data.titleItem
            Glide.with(context).load(data.photoItem).into(binding.imageProductLogo)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<LogoItem>, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

}

