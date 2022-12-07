package uz.gita.bookapi.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.bookapi.data.model.ResultData
import uz.gita.bookapi.data.remote.dto.auth.request.SignInRequest
import uz.gita.bookapi.domain.navigation.Navigator
import uz.gita.bookapi.domain.repository.AuthRepository
import uz.gita.bookapi.domain.usecase.BaseSignUseCase
import uz.gita.bookapi.domain.usecase.CheckPasswordUseCase
import uz.gita.bookapi.presentation.login.LoginScreenDirections
import uz.gita.bookapi.presentation.login.vm.LoginScreenViewModel
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModelImpl @Inject constructor(
    private val navigator: Navigator,
    private val repository: AuthRepository,
    private val checkPasswordUseCase: CheckPasswordUseCase,
    private val numberUseCase: BaseSignUseCase,
) : LoginScreenViewModel, ViewModel() {

    override val isLoading =
        MutableSharedFlow<Boolean>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)
    override val isConnecting =
        MutableSharedFlow<Boolean>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)
    override val errorMsg =
        MutableSharedFlow<String>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)
    override val message =
        MutableSharedFlow<Boolean>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)

    override fun openSignUpScreen() {
        viewModelScope.launch {
            navigator.navigateTo(LoginScreenDirections.actionLoginScreenToSignUpScreen())
        }
    }

    override fun openMainScreen(signInRequest: SignInRequest) {
        val checkPassword = checkPasswordUseCase.checkPassword(signInRequest.password)
        val checkNumber = numberUseCase.checkNumber(signInRequest.phone)
        viewModelScope.launch {
            if (checkPassword && checkNumber.isNotEmpty()) {
                repository.signIn(SignInRequest(checkNumber, signInRequest.password))
                    .collectLatest {
                        when (it) {
                            is ResultData.Fail -> errorMsg.emit(it.message)
                            is ResultData.HasConnection -> isConnecting.emit(it.hasConnection)
                            is ResultData.Loading -> isLoading.emit(it.isLoading)
                            is ResultData.Success -> navigator.navigateTo(LoginScreenDirections.actionLoginScreenToMainScreen())
                        }
                    }
                message.emit(true)
            } else message.emit(false)
        }
    }
}