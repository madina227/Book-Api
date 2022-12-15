package uz.gita.bookapi.data.local.room

import androidx.room.*

/**
 * @author : Madina Agzamova
 * @mailto : madina.agzamova.dev@gmail.com
 * @created : 14/12/2022, Wednesday, 15:26
 **/
@Dao
interface BookDao {

    @Insert
    fun insert(bookEntity: BookEntity)

    @Insert
    fun insert(books: List<BookEntity>)

//    @Delete
//    fun delete(id: Int)

    @Update
    fun update(bookEntity: BookEntity)

    @Query("DELETE FROM BookEntity")
    fun delete()

    @Query("SELECT * FROM BookEntity WHERE state !=2")
    fun getBooks(): List<BookEntity>
}