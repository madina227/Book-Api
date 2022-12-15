package uz.gita.bookapi.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.bookapi.data.local.room.BookDatabase
import javax.inject.Singleton

/**
 * @author : Madina Agzamova
 * @mailto : madina.agzamova.dev@gmail.com
 * @created : 14/12/2022, Wednesday, 15:39
 **/
@Module
@InstallIn(SingletonComponent::class)
class LocaleDatabaseModule {

    @Provides
    @Singleton
    fun providesLocaleDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, BookDatabase::class.java, "AppDatabase").build()

    @Provides
    @Singleton
    fun providesDao(db: BookDatabase) = db.bookDao()
}