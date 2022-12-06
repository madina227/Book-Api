package uz.gita.bookapi.domain.usecase

import android.util.Log
import javax.inject.Inject

class BaseSignUseCaseImpl @Inject constructor() : BaseSignUseCase {

    override fun checkNumber(number: String): String {
        val checkedNumber = StringBuilder()
        for (char in number) {
            if (char != '(' && char != ')') {
                checkedNumber.append(char)
            }
        }
        Log.d("LLL", "$checkedNumber")
        return if (checkedNumber.length == 13) {
            checkedNumber.toString()
        } else ""
    }
}