package uz.gita.bookapi.presentation.signUp.vm

import kotlinx.coroutines.flow.Flow
import uz.gita.bookapi.data.remote.dto.auth.request.SignUpRequest


/**
 * @author : Madina Agzamova
 * @mailto : madina.agzamova.dev@gmail.com
 * @created : 06/12/2022, Tuesday, 12:14
 **/
interface SignUpScreenViewModel {

    val isLoading: Flow<Boolean>
    val isConnecting: Flow<Boolean>
    val errorMsg: Flow<String>

    fun register(signUpRequest: SignUpRequest)
    fun openLogInScreen()
}