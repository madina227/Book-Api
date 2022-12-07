package uz.gita.bookapi.presentation.signUp.vm

import uz.gita.bookapi.data.remote.dto.auth.request.SignUpVerifyRequest

/**
 * @author : Madina Agzamova
 * @mailto : madina.agzamova.dev@gmail.com
 * @created : 06/12/2022, Tuesday, 14:33
 **/
interface VerifyScreenViewModel {

    fun checkCode(signUpVerifyRequest: SignUpVerifyRequest)
    fun back()
}