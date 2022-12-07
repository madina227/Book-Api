package uz.gita.bookapi.data.repositoryImpl

import android.content.Context
import kotlinx.coroutines.flow.Flow
import uz.gita.bookapi.data.local.Shp
import uz.gita.bookapi.data.model.ResultData
import uz.gita.bookapi.data.remote.dto.book.request.ChangeFavRequest
import uz.gita.bookapi.data.remote.dto.book.request.DeleteBookRequest
import uz.gita.bookapi.data.remote.dto.user.request.PostUserBooksRequest
import uz.gita.bookapi.data.remote.service.BookApi
import uz.gita.bookapi.domain.repository.BookRepository

class BookRepositoryImpl(
    private val bookApi: BookApi,
    private val shp: Shp,
    val context: Context
) : BookRepository {

    override suspend fun postBook(): Flow<ResultData<Unit>> {
        TODO("Not yet implemented")
    }

    override suspend fun getBooks(getBooksRequest: PostUserBooksRequest): Flow<ResultData<Unit>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteBook(deleteBookRequest: DeleteBookRequest): Flow<ResultData<Unit>> {
        TODO("Not yet implemented")
    }

    override suspend fun putBook(putBookRequest: DeleteBookRequest): Flow<ResultData<Unit>> {
        TODO("Not yet implemented")
    }

    override suspend fun changeFavBook(changeFavRequest: ChangeFavRequest): Flow<ResultData<Unit>> {
        TODO("Not yet implemented")
    }
}