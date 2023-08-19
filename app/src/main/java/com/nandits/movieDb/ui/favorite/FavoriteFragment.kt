package com.nandits.movieDb.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nandits.movieDb.R
import com.nandits.movieDb.data.base.BaseFragment
import com.nandits.movieDb.databinding.FragmentFavoriteBinding
import com.nandits.movieDb.ui.detail.DetailFragment
import com.nandits.movieDb.utils.DataMapper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {

    private val favViewModel: FavoriteViewModel by viewModels()
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun initUI() {
        binding.materialToolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        binding.btnToMain.setOnClickListener { findNavController().navigateUp() }
        with(binding.rvFav) {
            val favAdapter = FavoriteAdapter()
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favAdapter
            setHasFixedSize(true)
            favViewModel.getFavorite().observe(viewLifecycleOwner) {
                favAdapter.setData(DataMapper.mapEntityToModel(it))
                binding.imgEmpty.isGone = it.isNotEmpty()
                binding.btnToMain.isGone = it.isNotEmpty()
            }
            favAdapter.onItemClick = {
                findNavController().navigate(
                    R.id.action_favoriteFragment_to_detailFragment,
                    bundleOf(DetailFragment.TO_DETAIL to it)
                )
            }
        }
    }

    override fun initAction() {

    }
}