package com.example.bilemonline.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bilemonline.data.model.FeedbackTrial
import com.example.bilemonline.databinding.ItemFeedbacksBinding

class FeedbackAdapter : RecyclerView.Adapter<FeedbackAdapter.ViewHolder>() {

    private val feedbacks = listOf(
        FeedbackTrial("Назгуль апа", 5.0F, "Аябай сонун курс. Коп нерсени уйрондум"),
        FeedbackTrial("Сыймык Жып", 4.0F, "Весьма неплохое содержание"),
    )

    private lateinit var binding: ItemFeedbacksBinding

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemFeedbacksBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val feedback = feedbacks[position]

        holder.itemView.apply {
            binding.tvAuhtor.text = feedback.username
            binding.ratedResult.rating = feedback.rating
            binding.tvFeedbackDescription.text = feedback.feedbackText
        }
    }

    override fun getItemCount(): Int {
        return feedbacks.size
    }
}