package uz.gita.bookapi.data.remote.dto.book.response

data class PostBookResponse(
    val id: Int,
    val title: String,
    val author: String,
    val description: String,
    val pageCount: Int,
    val isFav: Boolean
)
