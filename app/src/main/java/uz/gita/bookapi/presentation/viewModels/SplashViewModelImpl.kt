package uz.gita.bookapi.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import uz.gita.bookapi.domain.navigation.Navigator
import uz.gita.bookapi.domain.repository.AuthRepository
import uz.gita.bookapi.presentation.splash.SplashScreenDirections
import uz.gita.bookapi.presentation.splash.vm.SplashViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    private val repository: AuthRepository,
    private val navigator: Navigator
) :
    SplashViewModel, ViewModel() {
    override val isFirstFlow =
        MutableSharedFlow<Boolean>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)

    init {
        viewModelScope.launch {
            if (repository.isFirst()) {
                navigator.navigateTo(SplashScreenDirections.actionSplashScreenToLoginScreen())
            } else{
                navigator.navigateTo(SplashScreenDirections.actionSplashScreenToBaseScreen())
            }

        }
    }
}