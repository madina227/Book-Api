package uz.gita.bookapi.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.bookapi.data.model.ResultData
import uz.gita.bookapi.data.remote.dto.auth.request.SignUpRequest
import uz.gita.bookapi.domain.repository.AuthRepository
import uz.gita.bookapi.domain.usecase.BaseSignUseCase
import uz.gita.bookapi.domain.usecase.SignUpUseCase
import uz.gita.bookapi.presentation.signUp.vm.SignUpScreenViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpScreenViewModelImpl @Inject constructor(
    private val repository: AuthRepository,
    private val useCase: SignUpUseCase,
    private val numberUseCase: BaseSignUseCase
) : SignUpScreenViewModel, ViewModel() {

    override val isLoading = MutableSharedFlow<Boolean>()
    override val isConnecting = MutableSharedFlow<Boolean>()
    override val errorMsg = MutableSharedFlow<String>()

    override fun register(signUpRequest: SignUpRequest) {
        viewModelScope.launch {
            repository.signUp(signUpRequest).collectLatest {
                when (it) {
                    is ResultData.Fail -> errorMsg.emit(it.message)
                    is ResultData.HasConnection -> isConnecting.emit(it.hasConnection)
                    is ResultData.Loading -> isLoading.emit(it.isLoading)
                    is ResultData.Success -> openLogInScreen()
                }
            }
        }
    }

    override fun openLogInScreen() {
        TODO("Not yet implemented")
    }
}