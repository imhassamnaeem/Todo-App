package com.example.todoapp.presentation.favourite

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.core.shared.base.BaseFragment
import com.example.todoapp.databinding.FragmentFavouritBinding
import com.example.todoapp.core.shared.extensions.loadAndCollectOnStarted
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteFragment : BaseFragment<FragmentFavouritBinding, FavouriteViewModel>(
    FragmentFavouritBinding::inflate
) {
    override val viewModel: FavouriteViewModel by viewModels()
    private lateinit var favouriteAdapter: FavouriteAdapter
    override fun setUpViews() {
        setUpFavouriteRecyclerView()
    }

    fun setUpFavouriteRecyclerView() {
        favouriteAdapter = FavouriteAdapter(
            onFavouriteClick = { task ->
                viewModel.removeFromFavourite(task)
            }
        )
        binding.favouriteRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favouriteAdapter
        }
    }

    override fun observeData() {
        loadAndCollectOnStarted()
        {
            viewModel.favouriteTasks.collect { tasks ->
                favouriteAdapter.submitList(tasks)
            }
        }
    }
}