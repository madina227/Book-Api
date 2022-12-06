package uz.gita.bookapi.domain.usecase

/**
 * @author : Madina Agzamova
 * @mailto : madina.agzamova.dev@gmail.com
 * @created : 06/12/2022, Tuesday, 12:06
 **/
interface BaseSignUseCase {
    fun checkNumber(number: String): String
}