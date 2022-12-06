package uz.gita.bookapi.domain.repository

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import uz.gita.bookapi.data.model.ResultData
import uz.gita.bookapi.data.remote.dto.auth.request.SignInRequest
import uz.gita.bookapi.data.remote.dto.auth.request.SignInVerifyRequest
import uz.gita.bookapi.data.remote.dto.auth.request.SignUpRequest
import uz.gita.bookapi.data.remote.dto.auth.request.SignUpVerifyRequest
import uz.gita.bookapi.data.remote.dto.auth.response.SignInResponse
import uz.gita.bookapi.data.remote.dto.auth.response.SignInVerifyResponse
import uz.gita.bookapi.data.remote.dto.auth.response.SignUpResponse
import uz.gita.bookapi.data.remote.dto.auth.response.SignUpVerificationResponse

interface AuthRepository {

    suspend fun signUp(signUpRequest: SignUpRequest): Flow<ResultData<Unit>>

    suspend fun signUpVerify(signUpVerifyRequest: SignUpVerifyRequest): Flow<ResultData<Unit>>

    suspend fun signIn(signInRequest: SignInRequest): Flow<ResultData<Unit>>

    suspend fun signInVerify(signInVerifyRequest: SignInVerifyRequest): Flow<ResultData<Unit>>
}