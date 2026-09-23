package com.example.todoapp.presentation.onboarding

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentOnBoardingBinding
import com.example.todoapp.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OnBoardingFragment : BaseFragment<FragmentOnBoardingBinding, OnboardingViewModel>(
    FragmentOnBoardingBinding::inflate
) {
    override val viewModel: OnboardingViewModel by viewModels()
    override fun setUpViews() {
        binding.btnGetStarted.setOnClickListener {
            viewModel.onGetStartedClick()
        }
    }

    override fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(
                Lifecycle.State.STARTED
            ) {

                viewModel.onboardingEvent.collect { event ->
                    when (event) {
                        is OnboardingEvent.NavigateToHome ->
                            findNavController().navigate(R.id.action_onBoardingFragment_to_homeFragment)
                    }
                }
            }
        }
    }
}