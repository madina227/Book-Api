package uz.gita.bookapi.data.remote.service

import retrofit2.Response
import retrofit2.http.*
import uz.gita.bookapi.data.remote.dto.book.request.ChangeFavRequest
import uz.gita.bookapi.data.remote.dto.book.request.DeleteBookRequest
import uz.gita.bookapi.data.remote.dto.book.request.PostBookRequest
import uz.gita.bookapi.data.remote.dto.book.request.PutBookRequest
import uz.gita.bookapi.data.remote.dto.book.response.*

interface BookApi {

    @POST("book")
    suspend fun postBook(
        @Body postBookRequest: PostBookRequest
    ): Response<PostBookResponse>

    //headerdan beriladgan malumotti yani tokenni interceptordan bervorsa boladi, shunaqa yozamiz
    @GET("books")
    suspend fun getBooks(
//        @Header("Authorization") bearerToken: String,
    ): Response<AllBooks>

    @HTTP(method = "DELETE", path = "book", hasBody = true)
    suspend fun deleteBook(
        @Body deleteBookRequest: DeleteBookRequest
    ): Response<DeleteBookResponse>

    @PUT("book")
    suspend fun putBook(
        @Body putBookRequest: PutBookRequest
    ): Response<PutBookResponse>

    @POST("book/change-fav")
    suspend fun changeFavBook(
        @Body changeFavRequest: ChangeFavRequest
    ): Response<ChangeFavResponse>

}