package uz.gita.bookapi.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.bookapi.data.repositoryImpl.AuthRepositoryImpl
import uz.gita.bookapi.domain.repository.AuthRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository
}