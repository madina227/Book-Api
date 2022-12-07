package uz.gita.bookapi.data.repositoryImpl

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.bookapi.data.local.Shp
import uz.gita.bookapi.data.model.ResultData
import uz.gita.bookapi.data.remote.dto.auth.request.SignInRequest
import uz.gita.bookapi.data.remote.dto.auth.request.SignInVerifyRequest
import uz.gita.bookapi.data.remote.dto.auth.request.SignUpRequest
import uz.gita.bookapi.data.remote.dto.auth.request.SignUpVerifyRequest
import uz.gita.bookapi.data.remote.service.AuthApi
import uz.gita.bookapi.domain.repository.AuthRepository
import uz.gita.bookapi.utils.hasConnection
import javax.inject.Inject

class AuthRepositoryImpl : AuthRepository {
    override suspend fun signUp(signUpRequest: SignUpRequest): Flow<ResultData<Unit>> =
        flow<ResultData<Unit>> {
            if (hasConnection(context = context)) {
                emit(ResultData.HasConnection(true))
                val response = authApi.signUp(signUpRequest)
                emit(ResultData.Loading(true))
                if (response.isSuccessful) {
                    response.body()?.let {
                        shp.token = it.token
                        emit(ResultData.Success(Unit))
                        emit(ResultData.Loading(false))
                    }
                } else {
                    response.body()?.let {
                        emit(ResultData.Fail(it.token))
                        emit(ResultData.Loading(false))
                    }
                }
            } else {
                emit(ResultData.HasConnection(false))
            }
        }.catch { error ->
            emit(ResultData.Loading(false))
            error.message?.let { msg ->
                emit(ResultData.Fail(msg))
            }

        }.flowOn(IO)

    override suspend fun signUpVerify(signUpVerifyRequest: SignUpVerifyRequest): Flow<ResultData<Unit>> =
        flow<ResultData<Unit>> {
            if (hasConnection(context)) {
                emit(ResultData.HasConnection(true))
                val response = authApi.signUpVerify(shp.token, signUpVerifyRequest)
                emit(ResultData.Loading(true))
                if (response.isSuccessful) {
                    response.body()?.let {
                        shp.token = it.token
                        emit(ResultData.Success(Unit))
                        emit(ResultData.Loading(false))
                    }
                } else {
                    response.body()?.let {
                        emit(ResultData.Fail(it.token))
                    }
                    emit(ResultData.Loading(false))
                }

            } else {
                emit(ResultData.HasConnection(false))
            }
        }.catch { error ->
            emit(ResultData.Loading(false))
            error.message?.let { msg ->
                emit(ResultData.Fail(msg))
            }
        }.flowOn(IO)

    override suspend fun signIn(signInRequest: SignInRequest): Flow<ResultData<Unit>> =
        flow<ResultData<Unit>> {
            if (hasConnection(context)) {
                emit(ResultData.HasConnection(true))
                val response = authApi.signIn(signInRequest)
                emit(ResultData.Loading(true))
                if (response.isSuccessful) {
                    response.body()?.let {
                        shp.token = it.token
                        emit(ResultData.Success(Unit))
                        emit(ResultData.Loading(false))
                    }
                } else {
                    emit(ResultData.Loading(false))
                    response.body()?.let {
                        emit(ResultData.Fail(it.token))
                    }
                }
            } else {
                emit(ResultData.HasConnection(false))
            }
        }.catch { error ->
            emit(ResultData.Loading(false))
            error.message?.let { msg ->
                emit(ResultData.Fail(msg))
            }
        }.flowOn(IO)

    override suspend fun signInVerify(signInVerifyRequest: SignInVerifyRequest): Flow<ResultData<Unit>> =
        flow<ResultData<Unit>> {
            if (hasConnection(context)) {
                emit(ResultData.HasConnection(true))
                val response = authApi.signInVerify(shp.token, signInVerifyRequest)
                emit(ResultData.Loading(true))
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(ResultData.Success(Unit))
                        shp.token = it.token
                        emit(ResultData.Loading(false))
                    }
                } else {
                    emit(ResultData.Loading(false))
                    response.body()?.let {
                        emit(ResultData.Fail(it.token))
                    }
                }
            } else {
                emit(ResultData.HasConnection(false))
            }
        }.catch { error ->
            emit(ResultData.Loading(false))
            error.message?.let { msg ->
                emit(ResultData.Fail(msg))
            }
        }.flowOn(IO)
}