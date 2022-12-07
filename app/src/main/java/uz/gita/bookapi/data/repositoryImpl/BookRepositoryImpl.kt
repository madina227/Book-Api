package uz.gita.bookapi.data.repositoryImpl

import kotlinx.coroutines.flow.Flow
import uz.gita.bookapi.data.model.ResultData
import uz.gita.bookapi.data.remote.dto.book.request.ChangeFavRequest
import uz.gita.bookapi.data.remote.dto.book.request.DeleteBookRequest
import uz.gita.bookapi.data.remote.dto.user.request.PostUserBooksRequest
import uz.gita.bookapi.domain.repository.BookRepository

class BookRepositoryImpl : BookRepository {

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