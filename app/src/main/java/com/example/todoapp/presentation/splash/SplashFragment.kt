package com.example.todoapp.presentation.splash

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.core.shared.base.BaseFragment
import com.example.todoapp.databinding.FragmentSplashBinding
import com.example.todoapp.core.shared.extensions.loadAndCollectOnStarted
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>(
    FragmentSplashBinding::inflate
) {
    override val viewModel: SplashViewModel by viewModels()

    override fun setUpViews() {
        viewModel.checkSplash()
    }

    override fun observeData() {
        loadAndCollectOnStarted {
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
