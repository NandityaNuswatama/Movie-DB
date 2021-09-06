package com.nandits.parkee_movie.ui.main

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nandits.parkee_movie.R
import com.nandits.parkee_movie.data.Resource
import com.nandits.parkee_movie.data.model.MovieModel
import com.nandits.parkee_movie.databinding.FragmentMainBinding
import com.nandits.parkee_movie.ui.detail.DetailFragment
import com.nandits.parkee_movie.ui.main.adapter.NowPlayingAdapter
import com.nandits.parkee_movie.ui.main.adapter.PopularAdapter
import com.nandits.parkee_movie.ui.main.adapter.TopRatedAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by viewModels()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.materialToolbar)
        loadData()
    }
    
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.favMenu) findNavController().navigate(R.id.action_mainFragment_to_favoriteFragment)
        return true
    }
    
    private fun loadData() {
        mainViewModel.getPopular().observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Loading -> {
                    loading(true)
                }
                is Resource.Error -> {
                    loading(false)
                    Timber.w(response.message)
                }
                is Resource.Success -> {
                    loading(false)
                    if (response.data != null) {
                        setRvPopular(response.data)
                    } else Timber.w("data null")
                }
            }
        })
        mainViewModel.getNowPlaying().observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Loading -> {
                    loading(true)
                }
                is Resource.Error -> {
                    loading(false)
                    Timber.w(response.message)
                }
                is Resource.Success -> {
                    loading(false)
                    if (response.data != null) {
                        setRvNowPlaying(response.data)
                    } else Timber.w("data null")
                }
            }
        })
        mainViewModel.getTopRated().observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Loading -> {
                    loading(true)
                }
                is Resource.Error -> {
                    loading(false)
                    Timber.w(response.message)
                }
                is Resource.Success -> {
                    loading(false)
                    if (response.data != null) {
                        setRvTopRated(response.data)
                    } else Timber.w("data null")
                }
            }
        })
        
    }
    
    private fun setRvPopular(data: ArrayList<MovieModel>) {
        Timber.i(data.toString())
        with(binding.rvPopular) {
            val popularAdapter = PopularAdapter()
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = popularAdapter
            setHasFixedSize(true)
            popularAdapter.setData(data)
            popularAdapter.onItemClick = {
                findNavController().navigate(
                    R.id.action_mainFragment_to_detailFragment,
                    bundleOf(DetailFragment.TO_DETAIL to it)
                )
            }
        }
    }
    
    private fun setRvNowPlaying(data: ArrayList<MovieModel>) {
        Timber.i(data.toString())
        with(binding.rvNowPlaying) {
            val nowPlayingAdapter = NowPlayingAdapter()
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = nowPlayingAdapter
            setHasFixedSize(true)
            nowPlayingAdapter.setData(data)
            nowPlayingAdapter.onItemClick = {
                findNavController().navigate(
                    R.id.action_mainFragment_to_detailFragment,
                    bundleOf(DetailFragment.TO_DETAIL to it)
                )
            }
        }
    }
    
    private fun setRvTopRated(data: ArrayList<MovieModel>) {
        Timber.i(data.toString())
        with(binding.rvTopRated) {
            val topRatedAdapter = TopRatedAdapter()
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = topRatedAdapter
            setHasFixedSize(true)
            topRatedAdapter.setData(data)
            topRatedAdapter.onItemClick = {
                findNavController().navigate(
                    R.id.action_mainFragment_to_detailFragment,
                    bundleOf(DetailFragment.TO_DETAIL to it)
                )
            }
        }
    }
    
    private fun loading(boolean: Boolean) {
        binding.progressBar.isGone = !boolean
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}