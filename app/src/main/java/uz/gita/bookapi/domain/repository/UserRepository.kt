package uz.gita.bookapi.domain.repository

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import uz.gita.bookapi.data.model.ResultData
import uz.gita.bookapi.data.remote.dto.user.request.PostRateRequest
import uz.gita.bookapi.data.remote.dto.user.request.PostUserBooksRequest
import uz.gita.bookapi.data.remote.dto.user.response.AllUsers
import uz.gita.bookapi.data.remote.dto.user.response.PostRateResponse
import uz.gita.bookapi.data.remote.dto.user.response.PostUserBooksResponse

/**
 * @author : Madina Agzamova
 * @mailto : madina.agzamova.dev@gmail.com
 * @created : 13/12/2022, Tuesday, 12:04
 **/
interface UserRepository {

    suspend fun getUsers(): Flow<ResultData<AllUsers>>

    suspend fun postUser(postUserBooksRequest: PostUserBooksRequest): Response<PostUserBooksResponse>

    suspend fun rateBook(postRateRequest: PostRateRequest): Response<PostRateResponse>
}