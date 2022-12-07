package uz.gita.bookapi.data.remote.service

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import uz.gita.bookapi.data.remote.dto.user.request.PostRateRequest
import uz.gita.bookapi.data.remote.dto.user.request.PostUserBooksRequest
import uz.gita.bookapi.data.remote.dto.user.response.GetUsersResponse
import uz.gita.bookapi.data.remote.dto.user.response.PostRateResponse
import uz.gita.bookapi.data.remote.dto.user.response.PostUserBooksResponse

interface UserApi {

    @GET("books/users")
    suspend fun getUsers(): Response<GetUsersResponse>

    @POST("books/user")
    suspend fun postUser(
        @Body postUserBooksRequest: PostUserBooksRequest
    ): Response<PostUserBooksResponse>

    @POST("book/rate")
    suspend fun rateBook(
        @Body postRateRequest: PostRateRequest
    ): Response<PostRateResponse>
}