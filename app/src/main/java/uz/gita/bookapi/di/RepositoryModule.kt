package uz.gita.bookapi.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.bookapi.data.repositoryImpl.AuthRepositoryImpl
import uz.gita.bookapi.data.repositoryImpl.BookRepositoryImpl
import uz.gita.bookapi.data.repositoryImpl.UserRepositoryImpl
import uz.gita.bookapi.domain.repository.AuthRepository
import uz.gita.bookapi.domain.repository.BookRepository
import uz.gita.bookapi.domain.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun provideAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    fun provideBookRepository(
        bookRepositoryImpl: BookRepositoryImpl
    ): BookRepository

    @Binds
    @Singleton
    fun provideUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

}