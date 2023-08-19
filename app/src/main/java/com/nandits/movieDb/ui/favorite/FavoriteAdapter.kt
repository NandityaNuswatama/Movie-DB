package com.nandits.movieDb.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.nandits.movieDb.R
import com.nandits.movieDb.data.model.MovieModel
import com.nandits.movieDb.databinding.ItemFavoriteBinding
import com.nandits.movieDb.utils.imageLink

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {
    private var list = ArrayList<MovieModel>()
    var onItemClick: ((MovieModel) -> Unit)? = null
    
    fun setData(data: ArrayList<MovieModel>){
        list.clear()
        list = data
        notifyDataSetChanged()
    }
    
    inner class ViewHolder(private val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieModel) {
            with(binding){
                tvTitleFav.text = item.title
                tvRatingFav.text = item.vote_average
                tvDateFav.text = itemView.context.getString(R.string.release, item.releaseDate)
                imgFav.load(imageLink+item.poster){
                    placeholder(R.drawable.icon_image)
                    crossfade(true)
                    RoundedCornersTransformation(10f)
                }
                root.setOnClickListener {
                    onItemClick?.invoke(item)
                }
            }
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }
    
    override fun getItemCount(): Int {
        return list.size
    }
}