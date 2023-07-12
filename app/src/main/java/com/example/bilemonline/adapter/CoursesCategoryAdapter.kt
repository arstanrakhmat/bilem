package com.example.bilemonline.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bilemonline.databinding.ItemCoursesCategoryBinding

class CoursesCategoryAdapter(private val courses: List<CoursesCategory>) :
    RecyclerView.Adapter<CoursesCategoryAdapter.ViewHolder>() {

    private lateinit var binding: ItemCoursesCategoryBinding
//    var displayMetrics = DisplayMetrics()
//    private var screenWidth = 0

//    private val differCallback = object : DiffUtil.ItemCallback<Course>() {
//        override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
//            return oldItem.title == newItem.title
//        }
//
//        override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
//            return oldItem == newItem
//        }
//
//    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding =
            ItemCoursesCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

//        (parent.context as MainActivity).windowManager.defaultDisplay.getMetrics(displayMetrics)
//        screenWidth = displayMetrics.widthPixels

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val course = courses[position]
        holder.itemView.apply {
            binding.tvTitle.text = course.title
        }
    }

    override fun getItemCount(): Int {
        return courses.size
    }
}