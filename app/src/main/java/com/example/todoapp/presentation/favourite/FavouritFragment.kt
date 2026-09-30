package com.example.todoapp.presentation.favourite

import androidx.fragment.app.viewModels
import com.example.todoapp.core.shared.base.BaseFragment
import com.example.todoapp.core.shared.extensions.hide
import com.example.todoapp.core.shared.extensions.loadAndCollectOnStarted
import com.example.todoapp.core.shared.extensions.show
import com.example.todoapp.core.shared.extensions.showToast
import com.example.todoapp.databinding.FragmentFavouritBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteFragment : BaseFragment<FragmentFavouritBinding, FavouriteViewModel>(
    FragmentFavouritBinding::inflate
) {
    override val viewModel: FavouriteViewModel by viewModels()

    private val favouriteAdapter = FavouriteAdapter(
        onFavouriteClick = { task ->
            viewModel.removeFromFavourite(task)
            showToast("Task removed from favourites")
        }
    )

    override fun setUpViews() {
        binding.favouriteRecyclerView.adapter = favouriteAdapter
    }

    override fun observeData() {
        loadAndCollectOnStarted()
        {
            viewModel.favouriteTasks.collect { tasks ->
                favouriteAdapter.submitList(tasks)
                with(binding){
                    if (tasks.isEmpty()){
                        favouriteRecyclerView.hide()
                        emptyFavStateText.show()
                    }
                    else{
                        favouriteRecyclerView.show()
                        emptyFavStateText.hide()
                    }
                }
            }
        }
    }
}