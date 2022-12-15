package uz.gita.bookapi.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author : Madina Agzamova
 * @mailto : madina.agzamova.dev@gmail.com
 * @created : 14/12/2022, Wednesday, 15:26
 **/
@Entity
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    val localId: Int = 0,
    val id: Int,
    val title: String,
    val author: String,
    val description: String,
    val pageCount: Int,
    var fav: Boolean = false,
    val state: State,

    )
