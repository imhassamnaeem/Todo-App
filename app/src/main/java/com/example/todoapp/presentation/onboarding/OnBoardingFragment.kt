package com.example.todoapp.presentation.onboarding

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.core.shared.base.BaseFragment
import com.example.todoapp.databinding.FragmentOnBoardingBinding
import com.example.todoapp.core.shared.extensions.loadAndCollectOnStarted
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingFragment : BaseFragment<FragmentOnBoardingBinding, OnboardingViewModel>(
    FragmentOnBoardingBinding::inflate
) {
    override val viewModel: OnboardingViewModel by viewModels()

    override fun setUpViews() {
        binding.apply {
            btnGetStarted.setOnClickListener {
                viewModel.onGetStartedClick()
            }
        }
    }

    override fun observeData() {
        loadAndCollectOnStarted {
            viewModel.onboardingEvent.collect { event ->
                when (event) {
                    is OnboardingEvent.NavigateToHome ->
                        findNavController().navigate(R.id.action_onBoardingFragment_to_homeFragment)
                }
            }
        }
    }
}
