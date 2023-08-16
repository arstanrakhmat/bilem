package com.example.bilemonline.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bilemonline.data.model.GetSectionResponse
import com.example.bilemonline.data.model.Section
import com.example.bilemonline.databinding.ItemSectionBinding

class SectionAdapter : RecyclerView.Adapter<SectionAdapter.ViewHolder>() {

    private lateinit var binding: ItemSectionBinding

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Section>() {
        override fun areItemsTheSame(
            oldItem: Section,
            newItem: Section
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Section,
            newItem: Section
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemSectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val section = differ.currentList[position]

        binding.tvSectionName.text = section.theme
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}