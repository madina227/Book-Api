package uz.gita.bookapi.domain.usecase

import javax.inject.Inject

class CheckPasswordUseCaseImpl @Inject constructor(): CheckPasswordUseCase {
    override fun checkPassword(password: String): Boolean = password.length >= 6
}