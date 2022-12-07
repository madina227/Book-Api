package uz.gita.bookapi.data.remote.service

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import uz.gita.bookapi.data.remote.dto.auth.request.SignInRequest
import uz.gita.bookapi.data.remote.dto.auth.request.SignInVerifyRequest
import uz.gita.bookapi.data.remote.dto.auth.request.SignUpRequest
import uz.gita.bookapi.data.remote.dto.auth.request.SignUpVerifyRequest
import uz.gita.bookapi.data.remote.dto.auth.response.SignInResponse
import uz.gita.bookapi.data.remote.dto.auth.response.SignInVerifyResponse
import uz.gita.bookapi.data.remote.dto.auth.response.SignUpResponse

interface AuthApi {

    @POST("auth/sign-up")
    suspend fun signUp(
        @Body signUpRequest: SignUpRequest
    ): Response<SignUpResponse>

    @POST("auth/sign-up/verify")
    suspend fun signUpVerify(
        @Header("Authorization") bearerToken: String,
        @Body signUpVerifyRequest: SignUpVerifyRequest
    ): Response<SignUpResponse>

    @POST("auth/sign-in")
    suspend fun signIn(
        @Body signInRequest: SignInRequest
    ): SignInResponse

    @POST("auth/sign-in/verify")
    suspend fun signInVerify(
        @Header("Authorization") bearerToken: String,
        @Body signInVerifyRequest: SignInVerifyRequest
    ): Response<SignInVerifyResponse>
}