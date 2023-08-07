package com.example.bilemonline.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bilemonline.data.model.Data
import com.example.bilemonline.databinding.ItemFreeCourseBinding

class FreeCourseAdapter : RecyclerView.Adapter<FreeCourseAdapter.ViewHolder>() {

    private lateinit var binding: ItemFreeCourseBinding


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemFreeCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(course.logo?.url).centerCrop().into(
                binding.ivPhoto
            )
            binding.tvTitle.text = course.title
            binding.tvAuthor.text = course.authors?.get(0)?.username.toString()
            binding.tvRate.text = course.rating.toString()
//            binding.tvLearned.text = course.people_bought.toString()
            setOnClickListener {
                onItemClickListener?.let { it(course) }
            }
        }
    }

    private var onItemClickListener: ((Data) -> Unit)? = null

    fun setOnClickListener(listener: (Data) -> Unit) {
        onItemClickListener = listener
    }
}