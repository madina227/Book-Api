package uz.gita.bookapi.domain.usecase

/**
 * @author : Madina Agzamova
 * @mailto : madina.agzamova.dev@gmail.com
 * @created : 06/12/2022, Tuesday, 12:08
 **/
interface CheckPasswordUseCase {
    fun checkPassword(password: String): Boolean
}