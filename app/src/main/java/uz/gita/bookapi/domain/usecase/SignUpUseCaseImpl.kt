package uz.gita.bookapi.domain.usecase

class SignUpUseCaseImpl : SignUpUseCase {
    override fun checkName(name: String): Boolean = name.length >= 3

    override fun checkSamePassword(password1: String, password2: String): Boolean =
        password1.length >= 6 && password1 == password2
}
