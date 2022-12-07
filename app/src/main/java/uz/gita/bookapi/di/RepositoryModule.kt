package uz.gita.bookapi.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.bookapi.data.local.Shp
import uz.gita.bookapi.data.remote.service.AuthApi
import uz.gita.bookapi.data.remote.service.BookApi
import uz.gita.bookapi.data.repositoryImpl.AuthRepositoryImpl
import uz.gita.bookapi.data.repositoryImpl.BookRepositoryImpl
import uz.gita.bookapi.domain.repository.AuthRepository
import uz.gita.bookapi.domain.repository.BookRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        authApi: AuthApi,
        shp: Shp,
        @ApplicationContext context: Context
    ): AuthRepository {
        return AuthRepositoryImpl(authApi, context, shp)
    }

    @Provides
    @Singleton
    fun provideBookRepository(
        bookApi: BookApi,
        shp: Shp,
        @ApplicationContext context: Context
    ): BookRepository {
        return BookRepositoryImpl(bookApi, shp, context)
    }


}