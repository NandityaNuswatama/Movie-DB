package com.nandits.movieDb.ui.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.nandits.movieDb.R
import com.nandits.movieDb.data.Resource
import com.nandits.movieDb.data.base.BaseFragment
import com.nandits.movieDb.data.local.EntityMovie
import com.nandits.movieDb.data.model.MovieModel
import com.nandits.movieDb.data.model.ReviewModel
import com.nandits.movieDb.databinding.FragmentDetailBinding
import com.nandits.movieDb.utils.imageLink
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    companion object {
        const val TO_DETAIL = "to detail"
    }

    private val movie by lazy {
        arguments?.getParcelable<MovieModel>(TO_DETAIL) as MovieModel
    }

    private val detailViewModel: DetailViewModel by viewModels()

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun initUI() {
        setMovie()
        loadReview()
    }

    override fun initAction() {
        with(binding) {
            fabShare.setOnClickListener { shareAction() }
            detailViewModel.isFavorite(movie.id as Int).observe(viewLifecycleOwner) {
                setFav(it)
            }
        }
    }

    private fun setMovie() {
        with(binding) {
            toolBar.setNavigationOnClickListener { findNavController().navigateUp() }
            filmImage.load(imageLink + movie.poster) {
                crossfade(true)
                placeholder(R.drawable.icon_image)
            }
            tvTitleDetail.text = movie.title
            tvRatingDetail.text = movie.vote_average
            tvDate.text = movie.releaseDate
            tvOverview.text = movie.overview
        }
    }
    
    private fun loadReview() {
        movie.id?.let { detailViewModel.getReview(it) }?.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    loading(true)
                }

                is Resource.Error -> {
                    loading(false)
                    Timber.w(it.message)
                }

                is Resource.Success -> {
                    loading(false)
                    if (it.data != null) setReviewAdapter(it.data)
                }
            }
        }
    }
    
    private fun setReviewAdapter(data: ArrayList<ReviewModel>) {
        val reviewAdapter = ReviewAdapter()
        with(binding.rvReview) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = reviewAdapter
            reviewAdapter.setData(data)
        }
    }
    
    private fun loading(boolean: Boolean) {
        binding.loadingReview.isGone = !boolean
    }
    
    private fun dataEntity(): EntityMovie {
        return EntityMovie(
            id = movie.id as Int,
            title = movie.title as String,
            poster = movie.poster as String,
            backdrop = movie.backdrop as String,
            vote_average = movie.vote_average as String,
            release = movie.releaseDate as String,
            overview = movie.overview as String
        )
    }
    
    private fun shareAction() {
        val intent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, TextUtils.concat(movie.title))
        }
        val shareIntent = Intent.createChooser(intent, resources.getString(R.string.app_name))
        startActivity(shareIntent)
    }
    
    private fun insertMovie(){
        detailViewModel.insert(dataEntity())
        Toast.makeText(requireContext(), "Movie added to Favorite", Toast.LENGTH_SHORT).show()
    }
    
    private fun deleteMovie(){
        detailViewModel.delete(dataEntity())
        Toast.makeText(requireContext(), "Movie removed from Favorite", Toast.LENGTH_SHORT).show()
    }
    
    private fun setFav(bool: Boolean) {
        if (bool){
            binding.fabFav.setImageResource(R.drawable.icon_fav)
            binding.fabFav.setOnClickListener { deleteMovie() }
        }
        else {
            binding.fabFav.setImageResource(R.drawable.icon_fav_border)
            binding.fabFav.setOnClickListener { insertMovie() }
        }
    }
}