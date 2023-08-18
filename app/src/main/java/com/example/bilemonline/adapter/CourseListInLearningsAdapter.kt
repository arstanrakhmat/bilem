package com.example.bilemonline.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bilemonline.data.model.PassingCoursesItem
import com.example.bilemonline.databinding.ItemCourseInLearningsBinding

class CourseListInLearningsAdapter() :
    RecyclerView.Adapter<CourseListInLearningsAdapter.ViewHolder>() {

    private lateinit var binding: ItemCourseInLearningsBinding

    private val differCallback = object : DiffUtil.ItemCallback<PassingCoursesItem>() {
        override fun areItemsTheSame(
            oldItem: PassingCoursesItem,
            newItem: PassingCoursesItem
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: PassingCoursesItem,
            newItem: PassingCoursesItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding =
            ItemCourseInLearningsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val course = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(course.logo?.url).centerCrop().into(
                binding.ivPhoto
            )
            binding.tvTitle.text = course.title
            binding.tvAuthor.text = course.authors[0].username
            binding.tvRate.text = course.rating.toString()
            binding.tvLearned.text = course.students.toString()
            binding.tvPrice.text = course.price.toString()
            setOnClickListener {
                onItemClickListener?.let { it(course) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((PassingCoursesItem) -> Unit)? = null

    fun setOnClickListener(listener: (PassingCoursesItem) -> Unit) {
        onItemClickListener = listener
    }
}