package com.example.bilemonline.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bilemonline.R
import com.example.bilemonline.data.model.DataCategory
import com.example.bilemonline.databinding.ItemCategoryBinding
import java.util.Random

class CategoryAdapter :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private lateinit var binding: ItemCategoryBinding

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding.root)
    }

    private val differCallback = object : DiffUtil.ItemCallback<DataCategory>() {
        override fun areItemsTheSame(oldItem: DataCategory, newItem: DataCategory): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataCategory, newItem: DataCategory): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = differ.currentList[position]

        holder.itemView.apply {
            binding.tvTitle.text = category.name
            binding.tvQuantity.text = when (category.coursesQuantity) {
                1, 2, 3, 4 -> "${category.coursesQuantity} курса"
                else -> "${category.coursesQuantity} курсов"
            }
//            binding.llCategory.setBackgroundColor(
//                ContextCompat.getColor(
//                    holder.itemView.context,
//                    category.colorResId
//                )
//            )
            binding.llCategory.setBackgroundColor(category.getRandomColor(holder.itemView.context))
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun DataCategory.getRandomColor(context: Context): Int {
        val colors = listOf(
            R.color.custom_blue,
            R.color.custom_green,
            R.color.custom_pink,
            R.color.custom_purple,
            R.color.custom_yellow
        )
        return ContextCompat.getColor(context, colors[Random().nextInt(colors.size)])
    }

}