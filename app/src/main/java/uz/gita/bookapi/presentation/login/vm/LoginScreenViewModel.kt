package uz.gita.bookapi.presentation.login.vm

import kotlinx.coroutines.flow.Flow
import uz.gita.bookapi.data.remote.dto.auth.request.SignInRequest

/**
 * @author : Madina Agzamova
 * @mailto : madina.agzamova.dev@gmail.com
 * @created : 06/12/2022, Tuesday, 14:54
 **/
interface LoginScreenViewModel {

    val isLoading: Flow<Boolean>
    val isConnecting: Flow<Boolean>
    val errorMsg: Flow<String>
    val message: Flow<Boolean>

    suspend fun openSignUpScreen()

    suspend fun openMainScreen(signInRequest: SignInRequest)
}