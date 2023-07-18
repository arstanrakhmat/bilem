package com.example.bilemonline.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bilemonline.data.model.DataCategory
import com.example.bilemonline.databinding.ItemCoursesCategoryBinding

class CoursesCategoryAdapter :
    RecyclerView.Adapter<CoursesCategoryAdapter.ViewHolder>() {

    private lateinit var binding: ItemCoursesCategoryBinding
//    var displayMetrics = DisplayMetrics()
//    private var screenWidth = 0

    private val differCallback = object : DiffUtil.ItemCallback<DataCategory>() {
        override fun areItemsTheSame(oldItem: DataCategory, newItem: DataCategory): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataCategory, newItem: DataCategory): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding =
            ItemCoursesCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

//        (parent.context as MainActivity).windowManager.defaultDisplay.getMetrics(displayMetrics)
//        screenWidth = displayMetrics.widthPixels

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val course = differ.currentList[position]
        holder.itemView.apply {
            binding.tvTitle.text = course.name
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}