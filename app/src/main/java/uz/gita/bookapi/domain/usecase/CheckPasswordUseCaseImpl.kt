package uz.gita.bookapi.domain.usecase

class CheckPasswordUseCaseImpl : CheckPasswordUseCase {
    override fun checkPassword(password: String): Boolean = password.length >= 6
}