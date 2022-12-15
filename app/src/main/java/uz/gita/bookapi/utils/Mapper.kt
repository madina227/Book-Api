package uz.gita.bookapi.utils

import uz.gita.bookapi.data.local.room.BookEntity
import uz.gita.bookapi.data.local.room.State
import uz.gita.bookapi.data.remote.dto.book.response.GetBooksResponse

/**
 * @author : Madina Agzamova
 * @mailto : madina.agzamova.dev@gmail.com
 * @created : 14/12/2022, Wednesday, 16:30
 **/

fun BookEntity.entityToResponse() = GetBooksResponse(id, title, author, description, pageCount, fav)

fun GetBooksResponse.responseToEntity() = BookEntity(
    id = id,
    title = title,
    author = author,
    description = description,
    pageCount = pageCount,
    fav = isFav,
    state = State.UpToDate
)