package uz.gita.bookapi.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import uz.gita.bookapi.data.remote.dto.auth.request.SignUpVerifyRequest
import uz.gita.bookapi.domain.navigation.Navigator
import uz.gita.bookapi.domain.repository.AuthRepository
import uz.gita.bookapi.presentation.signUp.SignUpVerifyScreenDirections
import uz.gita.bookapi.presentation.signUp.vm.VerifyScreenViewModel
import javax.inject.Inject

@HiltViewModel
class VerifyScreenViewModelImpl @Inject constructor(
    private val repository: AuthRepository,
    private val navigator: Navigator
) : VerifyScreenViewModel, ViewModel() {

    override val isLoading =
        MutableSharedFlow<Boolean>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)
    override val isConnecting =
        MutableSharedFlow<Boolean>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)
    override val errorMsg =
        MutableSharedFlow<String>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)
    override val message =
        MutableSharedFlow<Boolean>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)

    override fun checkCode(signUpVerifyRequest: SignUpVerifyRequest) {
        viewModelScope.launch {
            repository.signUpVerify(signUpVerifyRequest)
            navigator.navigateTo(SignUpVerifyScreenDirections.actionSignUpVerifyScreenToLoginScreen())
        }
    }

    override fun back() {
        viewModelScope.launch {
            navigator.back()
        }
    }
}