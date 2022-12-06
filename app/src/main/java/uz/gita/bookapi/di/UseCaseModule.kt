package uz.gita.bookapi.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.bookapi.domain.usecase.*

/**
 * @author : Madina Agzamova
 * @mailto : madina.agzamova.dev@gmail.com
 * @created : 06/12/2022, Tuesday, 12:10
 **/

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {
    @Binds
    fun bindBaseUseCase(baseSignUseCaseImpl: BaseSignUseCaseImpl): BaseSignUseCase

    @Binds
    fun bindCheckPasswordUseCase(checkPasswordUseCaseImpl: CheckPasswordUseCaseImpl): CheckPasswordUseCase

    @Binds
    fun bindSignUpUseCase(signUpUseCaseImpl: SignUpUseCaseImpl): SignUpUseCase
}