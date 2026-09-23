package com.example.todoapp.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.core.datastore.OnboardingDataPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(private val onboardingPreferences: OnboardingDataPreferences) : ViewModel() {
private val _onboardingEvent = MutableSharedFlow<OnboardingEvent>()
    val onboardingEvent = _onboardingEvent.asSharedFlow()
 fun onGetStartedClick(){
     viewModelScope.launch {
         onboardingPreferences.setOnboardingCompleted()
         _onboardingEvent.emit(OnboardingEvent.NavigateToHome)
     }
 }
}
sealed class OnboardingEvent{
    data object  NavigateToHome : OnboardingEvent()
}