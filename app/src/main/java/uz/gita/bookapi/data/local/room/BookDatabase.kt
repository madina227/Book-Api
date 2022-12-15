package uz.gita.bookapi.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * @author : Madina Agzamova
 * @mailto : madina.agzamova.dev@gmail.com
 * @created : 14/12/2022, Wednesday, 15:37
 **/
@Database(entities = [BookEntity::class], version = 1, exportSchema = false)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}