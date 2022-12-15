package uz.gita.bookapi.data.repositoryImpl

import android.content.Context
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.bookapi.data.local.Shp
import uz.gita.bookapi.data.local.room.BookDao
import uz.gita.bookapi.data.local.room.BookEntity
import uz.gita.bookapi.data.model.ResultData
import uz.gita.bookapi.data.remote.dto.book.request.ChangeFavRequest
import uz.gita.bookapi.data.remote.dto.book.request.DeleteBookRequest
import uz.gita.bookapi.data.remote.dto.book.request.PostBookRequest
import uz.gita.bookapi.data.remote.dto.book.request.PutBookRequest
import uz.gita.bookapi.data.remote.dto.book.response.AllBooks
import uz.gita.bookapi.data.remote.dto.book.response.GetBooksResponse
import uz.gita.bookapi.data.remote.dto.book.response.PostBookResponse
import uz.gita.bookapi.data.remote.service.BookApi
import uz.gita.bookapi.domain.repository.BookRepository
import uz.gita.bookapi.utils.entityToResponse
import uz.gita.bookapi.utils.hasConnection
import uz.gita.bookapi.utils.responseToEntity
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookApi: BookApi,
    private val shp: Shp,
    private val dao: BookDao,
    @ApplicationContext private val context: Context
) : BookRepository {

    override suspend fun postBook(postBookRequest: PostBookRequest): Flow<ResultData<PostBookResponse>> =
        flow<ResultData<PostBookResponse>> {
            if (hasConnection(context = context)) {
                emit(ResultData.HasConnection(true))
                val response = bookApi.postBook(postBookRequest)
                emit(ResultData.Loading(true))
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(ResultData.Success(it))
                        emit(ResultData.Loading(false))
                    }
                } else {
                    response.errorBody()?.let {
                        emit(ResultData.Fail(it.string()))
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

        }.flowOn(Dispatchers.IO)


    override suspend fun getBooks(): Flow<ResultData<AllBooks>> =
        flow<ResultData<AllBooks>> {
            val list: ArrayList<BookEntity> = ArrayList() //local list
            if (hasConnection(context = context)) {
                emit(ResultData.HasConnection(true))
                val response = bookApi.getBooks()
                emit(ResultData.Loading(true))
                if (response.isSuccessful) {
                    response.body()?.let {
                        it.onEach {
                            list.add(it.responseToEntity())
                            Log.d("CHANGEF", "repository getAllBooks-> ${it.isFav}")
                        }
                        dao.delete()
                        dao.insert(list)
                        Log.d("CHANGEF", "repository getAllBooks-> ${it}")
                        emit(ResultData.Success(it))
                        emit(ResultData.Loading(false))
                    }
                } else {
                    response.errorBody()?.let {
                        emit(ResultData.Fail(it.string()))
                        emit(ResultData.Loading(false))
                    }
                }
            } else {
                val list: ArrayList<GetBooksResponse> = ArrayList()
                dao.getBooks().onEach { it ->
                    list.add(it.entityToResponse())
                }
                val response = AllBooks()
                response.addAll(list)
                emit(ResultData.Success(response))
                emit(ResultData.HasConnection(false))
            }
        }.catch { error ->
            emit(ResultData.Loading(false))
            error.message?.let { msg ->
                emit(ResultData.Fail(msg))
            }

        }.flowOn(Dispatchers.IO)

//    override suspend fun getBook(): Flow<List<GetBooksResponse>> = flow {
//        if (hasConnection())
//    }

    override suspend fun deleteBook(deleteBookRequest: DeleteBookRequest): Flow<ResultData<Unit>> =
        flow<ResultData<Unit>> {
            if (hasConnection(context = context)) {
                emit(ResultData.HasConnection(true))
                val response = bookApi.deleteBook(deleteBookRequest)
                emit(ResultData.Loading(true))
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(ResultData.Success(Unit))
                        emit(ResultData.Loading(false))
                    }
                } else {
                    response.errorBody()?.let {
                        emit(ResultData.Fail(it.string()))
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

        }.flowOn(Dispatchers.IO)

    override suspend fun putBook(putBookRequest: PutBookRequest): Flow<ResultData<Unit>> =
        flow<ResultData<Unit>> {
            if (hasConnection(context = context)) {
                emit(ResultData.HasConnection(true))
                val response = bookApi.putBook(putBookRequest)
                emit(ResultData.Loading(true))
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(ResultData.Success(Unit))
                        emit(ResultData.Loading(false))
                    }
                } else {
                    response.errorBody()?.let {
                        emit(ResultData.Fail(it.string()))
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

        }.flowOn(Dispatchers.IO)

    override suspend fun changeFavBook(changeFavRequest: ChangeFavRequest): Flow<ResultData<Unit>> =
        flow<ResultData<Unit>> {
            if (hasConnection(context = context)) {
                emit(ResultData.HasConnection(true))
                val response = bookApi.changeFavBook(changeFavRequest)
                emit(ResultData.Loading(true))
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(ResultData.Success(Unit))
                        emit(ResultData.Loading(false))
                    }
                } else {
                    response.errorBody()?.let {
                        emit(ResultData.Fail(it.toString()))
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

        }.flowOn(Dispatchers.IO)
}