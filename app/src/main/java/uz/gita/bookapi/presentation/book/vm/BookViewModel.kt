package uz.gita.bookapi.presentation.book.vm

import kotlinx.coroutines.flow.Flow
import uz.gita.bookapi.data.remote.dto.book.request.ChangeFavRequest
import uz.gita.bookapi.data.remote.dto.book.request.DeleteBookRequest
import uz.gita.bookapi.data.remote.dto.book.request.PostBookRequest
import uz.gita.bookapi.data.remote.dto.book.request.PutBookRequest
import uz.gita.bookapi.data.remote.dto.book.response.AllBooks
import uz.gita.bookapi.data.remote.dto.book.response.PostBookResponse

/**
 * @author : Madina Agzamova
 * @mailto : madina.agzamova.dev@gmail.com
 * @created : 12/12/2022, Monday, 15:29
 **/
interface BookViewModel {
    val isLoading: Flow<Boolean>
    val isConnecting: Flow<Boolean>
    val errorMsg: Flow<String>
    val message: Flow<Boolean>

    val getAllBooks: Flow<AllBooks>
    val postBookFlow:Flow<PostBookResponse>

    fun postBook(postBookRequest: PostBookRequest)

    fun getBooks()

    fun deleteBook(deleteBookRequest: DeleteBookRequest)

    fun putBook(putBookRequest: PutBookRequest)

    fun changeFavBook(changeFavRequest: ChangeFavRequest)
}