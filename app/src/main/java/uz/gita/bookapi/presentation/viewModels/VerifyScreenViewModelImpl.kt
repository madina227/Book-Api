package uz.gita.bookapi.presentation.viewModels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
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

    override suspend fun checkCode(signUpVerifyRequest: SignUpVerifyRequest) {
        repository.signUpVerify(signUpVerifyRequest)
        navigator.navigateTo(SignUpVerifyScreenDirections.actionSignUpVerifyScreenToLoginScreen())
    }

    override suspend fun back() {
        navigator.back()
    }
}