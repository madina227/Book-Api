package uz.gita.bookapi.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.bookapi.data.model.ResultData
import uz.gita.bookapi.data.remote.dto.auth.request.SignInRequest
import uz.gita.bookapi.data.remote.dto.auth.request.SignInVerifyRequest
import uz.gita.bookapi.data.remote.dto.auth.request.SignUpRequest
import uz.gita.bookapi.data.remote.dto.auth.request.SignUpVerifyRequest

interface AuthRepository {

    suspend fun signUp(signUpRequest: SignUpRequest): Flow<ResultData<Unit>>

    suspend fun signUpVerify(signUpVerifyRequest: SignUpVerifyRequest): Flow<ResultData<Unit>>

    suspend fun signIn(signInRequest: SignInRequest): Flow<ResultData<Unit>>

    suspend fun signInVerify(signInVerifyRequest: SignInVerifyRequest): Flow<ResultData<Unit>>

    suspend fun isFirst():Boolean
}