package uz.gita.bookapi.domain.usecase

import android.util.Log

class BaseSignUseCaseImpl : BaseSignUseCase {

    override fun checkNumber(number: String): Boolean {
        val checkedNumber = StringBuilder()
        for (char in number) {
            if (char != '(' && char != ')') {
                checkedNumber.append(char)
            }
        }
        Log.d("LLL", "$checkedNumber")

        return checkedNumber.length == 13
    }
}