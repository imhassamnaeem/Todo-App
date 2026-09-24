package com.example.todoapp.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.core.shared.datastore.OnboardingDataPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val onboardingPreferences: OnboardingDataPreferences
) : ViewModel() {

    private val _splashEvent = MutableSharedFlow<SplashEvent>()
    val splashEvent = _splashEvent.asSharedFlow()

    fun checkSplash() {
        viewModelScope.launch {
            delay(2000.milliseconds)
            val complete = onboardingPreferences.isOnboardingCompleted.first()
            if (complete) {
                _splashEvent.emit(
                    SplashEvent.OnNavigateToHome
                )
            } else {
                _splashEvent.emit(
                    SplashEvent.OnNavigateToOnBoarding
                )
            }
        }
    }
}

sealed class SplashEvent {
    data object OnNavigateToHome : SplashEvent()
    data object OnNavigateToOnBoarding : SplashEvent()
}