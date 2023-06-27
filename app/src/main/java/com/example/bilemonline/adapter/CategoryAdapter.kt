package com.example.bilemonline.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.bilemonline.data.model.Category
import com.example.bilemonline.databinding.ItemCategoryBinding

class CategoryAdapter(private val categories: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private lateinit var binding: ItemCategoryBinding

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]

        holder.itemView.apply {
            binding.tvTitle.text = category.title
            binding.tvQuantity.text = category.quantity
            binding.llCategory.setBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    category.colorResId
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

}