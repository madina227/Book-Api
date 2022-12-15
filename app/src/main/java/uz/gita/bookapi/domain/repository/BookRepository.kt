package uz.gita.bookapi.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.bookapi.data.model.ResultData
import uz.gita.bookapi.data.remote.dto.book.request.ChangeFavRequest
import uz.gita.bookapi.data.remote.dto.book.request.DeleteBookRequest
import uz.gita.bookapi.data.remote.dto.book.request.PostBookRequest
import uz.gita.bookapi.data.remote.dto.book.request.PutBookRequest
import uz.gita.bookapi.data.remote.dto.book.response.AllBooks
import uz.gita.bookapi.data.remote.dto.book.response.PostBookResponse

/**
 * @author : Madina Agzamova
 * @mailto : madina.agzamova.dev@gmail.com
 * @created : 07/12/2022, Wednesday, 13:38
 **/

interface BookRepository {

    suspend fun postBook(postBookRequest: PostBookRequest): Flow<ResultData<PostBookResponse>>

    suspend fun getBooks(): Flow<ResultData<AllBooks>>

//    suspend fun getBooks(): Flow<List<GetBooksResponse>>

    suspend fun deleteBook(deleteBookRequest: DeleteBookRequest): Flow<ResultData<Unit>>

    suspend fun putBook(putBookRequest: PutBookRequest): Flow<ResultData<Unit>>

    suspend fun changeFavBook(changeFavRequest: ChangeFavRequest): Flow<ResultData<Unit>>

}