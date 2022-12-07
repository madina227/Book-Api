package uz.gita.bookapi.presentation.viewModels

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import uz.gita.bookapi.core.BaseViewModel
import uz.gita.bookapi.data.remote.dto.auth.request.SignInRequest
import uz.gita.bookapi.domain.navigation.Navigator
import uz.gita.bookapi.domain.repository.AuthRepository
import uz.gita.bookapi.domain.usecase.BaseSignUseCase
import uz.gita.bookapi.domain.usecase.CheckPasswordUseCase
import uz.gita.bookapi.presentation.login.LoginScreenDirections
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModelImpl @Inject constructor(
    private val navigator: Navigator,
    private val repository: AuthRepository,
    private val checkPasswordUseCase: CheckPasswordUseCase,
    private val numberUseCase: BaseSignUseCase,
) : BaseViewModel() {

    val isLoading =
        MutableSharedFlow<Boolean>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)
    val isConnecting =
        MutableSharedFlow<Boolean>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)

    val message =
        MutableSharedFlow<Boolean>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)

    fun openSignUpScreen() {
        viewModelScope.launch {
            navigator.navigateTo(LoginScreenDirections.actionLoginScreenToSignUpScreen())
        }
    }

    fun openMainScreen(phone: String, password: String) {
        val isValidPassword = checkPasswordUseCase.checkPassword(password)
        val checkNumber = numberUseCase.checkNumber(phone)

        //todo o'zin validation qo'yvolarsan  man bu shared flow state flow qanaqa ishlatlishini blmiman uiga LIVEDATA ishlatardim
        // nme openMainScreen? singIn mas

        vmScope.launch {
            isLoading.emit(true)
            val result = repository.signIn(SignInRequest(checkNumber, password))
            isLoading.emit(false)
            navigator.navigateTo(LoginScreenDirections.actionLoginScreenToMainScreen())
        }
    }
}