package com.example.bilemonline.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bilemonline.data.model.Course
import com.example.bilemonline.databinding.ItemCourseInLearningsBinding

class CourseListInLearningsAdapter(private val courses: List<Course>) :
    RecyclerView.Adapter<CourseListInLearningsAdapter.ViewHolder>() {

    private lateinit var binding: ItemCourseInLearningsBinding
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
            ItemCourseInLearningsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

//        (parent.context as MainActivity).windowManager.defaultDisplay.getMetrics(displayMetrics)
//        screenWidth = displayMetrics.widthPixels

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val course = courses[position]
        holder.itemView.apply {
            Glide.with(this).load(course.image).centerCrop().into(
                binding.ivPhoto
            )
            binding.tvTitle.text = course.title
            binding.tvAuthor.text = course.author
            binding.tvRate.text = course.rating.toString()
            binding.tvLearned.text = course.people_bought.toString()
            binding.tvPrice.text = course.price.toString()
        }
    }

    override fun getItemCount(): Int {
        return courses.size
    }
}