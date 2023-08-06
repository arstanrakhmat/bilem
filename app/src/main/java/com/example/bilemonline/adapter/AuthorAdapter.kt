package com.example.bilemonline.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bilemonline.databinding.CourseAuthorsItemBinding

class AuthorAdapter : RecyclerView.Adapter<AuthorAdapter.ViewHolder>() {

    private val author = listOf("Arstan Rakhmatulaev", "Sam Smith")

    private lateinit var binding: CourseAuthorsItemBinding

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding =
            CourseAuthorsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val author = author[position]
        holder.itemView.apply {
            binding.tvAuhtor.text = author
        }
    }

    override fun getItemCount(): Int {
        return author.size
    }
}