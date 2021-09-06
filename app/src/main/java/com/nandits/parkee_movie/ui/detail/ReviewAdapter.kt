package com.nandits.parkee_movie.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nandits.parkee_movie.R
import com.nandits.parkee_movie.data.model.ReviewModel
import com.nandits.parkee_movie.databinding.ItemReviewBinding

class ReviewAdapter: RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {
    private var list = ArrayList<ReviewModel>()
    
    fun setData(data: ArrayList<ReviewModel>){
        list.clear()
        list = data
        notifyDataSetChanged()
    }
    
    inner class ViewHolder(private val binding: ItemReviewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ReviewModel){
            with(binding){
                tvAuthor.text = itemView.context.getString(R.string.author, item.author)
                tvContent.text = item.content
            }
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }
    
    override fun getItemCount(): Int {
        return list.size
    }
}