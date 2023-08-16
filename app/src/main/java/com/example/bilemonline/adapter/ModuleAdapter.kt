package com.example.bilemonline.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bilemonline.data.model.GetModuleResponseItem
import com.example.bilemonline.databinding.ItemModuleBinding

class ModuleAdapter : RecyclerView.Adapter<ModuleAdapter.ViewHolder>() {

    private lateinit var binding: ItemModuleBinding

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<GetModuleResponseItem>() {
        override fun areItemsTheSame(
            oldItem: GetModuleResponseItem,
            newItem: GetModuleResponseItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: GetModuleResponseItem,
            newItem: GetModuleResponseItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemModuleBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course = differ.currentList[position]
        holder.itemView.apply {

            binding.tvModuleName.text = "${course.order}. ${course.theme}"
            setOnClickListener {
                onItemClickListener?.let { it(course) }
            }
        }
    }

    private var onItemClickListener: ((GetModuleResponseItem) -> Unit)? = null

    fun setOnClickListener(listener: (GetModuleResponseItem) -> Unit) {
        onItemClickListener = listener
    }

}