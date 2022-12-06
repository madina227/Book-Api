package uz.gita.bookapi.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.bookapi.data.model.ResultData
import uz.gita.bookapi.data.remote.dto.auth.request.SignUpRequest
import uz.gita.bookapi.domain.navigation.Navigator
import uz.gita.bookapi.domain.repository.AuthRepository
import uz.gita.bookapi.domain.usecase.BaseSignUseCase
import uz.gita.bookapi.domain.usecase.SignUpUseCase
import uz.gita.bookapi.presentation.signUp.SignUpScreenDirections
import uz.gita.bookapi.presentation.signUp.vm.SignUpScreenViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpScreenViewModelImpl @Inject constructor(
    private val repository: AuthRepository,
    private val useCase: SignUpUseCase,
    private val numberUseCase: BaseSignUseCase,
    private val navigator: Navigator
) : SignUpScreenViewModel, ViewModel() {

    override val isLoading =
        MutableSharedFlow<Boolean>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)
    override val isConnecting =
        MutableSharedFlow<Boolean>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)
    override val errorMsg =
        MutableSharedFlow<String>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)
    override val message =
        MutableSharedFlow<Boolean>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)

    override suspend fun register(signUpRequest: SignUpRequest) {
        viewModelScope.launch {
            repository.signUp(signUpRequest).collectLatest {
                when (it) {
                    is ResultData.Fail -> errorMsg.emit(it.message)
                    is ResultData.HasConnection -> isConnecting.emit(it.hasConnection)
                    is ResultData.Loading -> isLoading.emit(it.isLoading)
                    is ResultData.Success -> navigator.navigateTo(SignUpScreenDirections.actionSignUpScreenToSignUpVerifyScreen())
                }
            }
        }
    }

    override suspend fun back() {
        navigator.back()
    }

    override suspend fun openVerifyScreen(
        firstName: String,
        lastName: String,
        number: String,
        password1: String,
        password2: String
    ) {
        if (useCase.checkName(firstName) && useCase.checkName(lastName) && useCase.checkSamePassword(
                password1,
                password2
            ) && numberUseCase.checkNumber(number).length > 10
        ) {
            message.emit(true)
            register(
                SignUpRequest(
                    firstName = firstName,
                    lastName = lastName,
                    phone = numberUseCase.checkNumber(number),
                    password = password1
                )
            )
        } else {
            message.emit(false)
        }
    }
}