package uz.gita.bookapi.presentation.signUp.vm

import kotlinx.coroutines.flow.Flow


/**
 * @author : Madina Agzamova
 * @mailto : madina.agzamova.dev@gmail.com
 * @created : 06/12/2022, Tuesday, 12:14
 **/
interface SignUpScreenViewModel {

    val isLoading: Flow<Boolean>
    val isConnecting: Flow<Boolean>
    val errorMsg: Flow<String>
    val message:Flow<Boolean>

    fun back()
    fun openVerifyScreen(firstName:String, lastName:String, number:String, password1:String, password2: String)
}