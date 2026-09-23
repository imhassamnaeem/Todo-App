package com.example.todoapp.presentation.splash

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.todoapp.databinding.FragmentSplashBinding
import com.example.todoapp.presentation.base.BaseFragment
import com.example.todoapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding,
        SplashViewModel>(FragmentSplashBinding::inflate) {
    override val viewModel: SplashViewModel by viewModels()

    override fun setUpViews() {
        viewModel.checkSplash()
    }

    override fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(
                Lifecycle.State.STARTED
            ) {
                viewModel.splashEvent.collect { event ->
                    when (event) {
                        SplashEvent.OnNavigateToHome -> {
                            findNavController().navigate(
                                R.id.action_splashFragment_to_homeFragment
                            )
                        }
                        SplashEvent.OnNavigateToOnBoarding -> {
                            findNavController().navigate(
                                R.id.action_splashFragment_to_onBoardingFragment
                            )
                        }
                    }
                }
            }
        }
    }
}