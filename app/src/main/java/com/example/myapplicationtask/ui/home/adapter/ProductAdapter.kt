package com.example.myapplicationtask.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplicationtask.R
import com.example.myapplicationtask.databinding.ItemProductBinding
import com.example.myapplicationtask.model.Product
import com.example.myapplicationtask.ui.home.HomeFragment
import com.example.myapplicationtask.utils.BaseViewHolder
import com.example.myapplicationtask.utils.getNavOptions

class ProductAdapter(
    private val items: List<Product>,
    private val context: Context,
    private val homeFragment: HomeFragment

) : RecyclerView.Adapter<BaseViewHolder<Product>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding = binding, context = context, homeFragment = homeFragment)
    }


    class ProductViewHolder(
        var binding: ItemProductBinding,
        val context: Context,
        val homeFragment: HomeFragment
    ) : BaseViewHolder<Product>(binding.root) {
        override fun bind(data: Product) {
            binding.titleProductItem.text = data.titleProduct
            binding.descriptionProductItem.text = data.urlProduct
            Glide.with(context)
                .load(data.photoProduct)
                .into(binding.imageProductItem)
            itemView.rootView.setOnClickListener {
                homeFragment.findNavController().navigate(R.id.logoFragment, null, getNavOptions)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Product>, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

}

