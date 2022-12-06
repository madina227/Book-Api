package uz.gita.bookapi.data.remote.service

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import uz.gita.bookapi.data.remote.dto.book.request.PostBookRequest
import uz.gita.bookapi.data.remote.dto.book.response.GetBooksResponse
import uz.gita.bookapi.data.remote.dto.book.response.PostBookResponse
import uz.gita.bookapi.data.remote.dto.user.request.PostUserBooksRequest

interface BookApi {

    @POST("book")
    suspend fun postBook(
        @Body postBookRequest: PostBookRequest
    ): Response<PostBookResponse>

    @GET("books")
    suspend fun getBooks(
        @Header("Authorization") bearerToken: String,
        @Body getBooksRequest: PostUserBooksRequest
    ): Response<GetBooksResponse>
}