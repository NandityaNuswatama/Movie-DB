package com.nandits.parkee_movie.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.nandits.parkee_movie.R
import com.nandits.parkee_movie.data.model.MovieModel
import com.nandits.parkee_movie.databinding.ItemTextBelowBinding
import com.nandits.parkee_movie.utils.imageLink

class NowPlayingAdapter: RecyclerView.Adapter<NowPlayingAdapter.ViewHolder>() {
    private var list = ArrayList<MovieModel>()
    var onItemClick: ((MovieModel) -> Unit)?= null
    
    fun setData(data: ArrayList<MovieModel>){
        list.clear()
        list = data
        notifyDataSetChanged()
    }
    
    inner class ViewHolder(private val binding: ItemTextBelowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieModel){
            with(binding){
                titleMovie.text = item.title
                descriptionMovie.text = item.releaseDate
                imgPoster.load(imageLink+item.poster){
                    RoundedCornersTransformation(20f)
                    placeholder(R.drawable.icon_image)
                    scale(Scale.FILL)
                    crossfade(true)
                }
                root.setOnClickListener {
                    onItemClick?.invoke(item)
                }
            }
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemTextBelowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }
    
    override fun getItemCount(): Int {
        return list.size
    }
}