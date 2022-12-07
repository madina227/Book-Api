package uz.gita.bookapi.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.bookapi.data.model.ResultData
import uz.gita.bookapi.data.remote.dto.book.request.ChangeFavRequest
import uz.gita.bookapi.data.remote.dto.book.request.DeleteBookRequest
import uz.gita.bookapi.data.remote.dto.user.request.PostUserBooksRequest

/**
 * @author : Madina Agzamova
 * @mailto : madina.agzamova.dev@gmail.com
 * @created : 07/12/2022, Wednesday, 13:38
 **/

interface BookRepository {

    suspend fun postBook(): Flow<ResultData<Unit>>

    suspend fun getBooks(getBooksRequest: PostUserBooksRequest): Flow<ResultData<Unit>>

    suspend fun deleteBook(deleteBookRequest: DeleteBookRequest): Flow<ResultData<Unit>>

    suspend fun putBook(putBookRequest: DeleteBookRequest): Flow<ResultData<Unit>>

    suspend fun changeFavBook(changeFavRequest: ChangeFavRequest): Flow<ResultData<Unit>>

}