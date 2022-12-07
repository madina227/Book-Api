package uz.gita.bookapi.core

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import retrofit2.HttpException
import java.net.UnknownHostException

abstract class BaseViewModel : ViewModel() {
    val errorMsg = MutableSharedFlow<String>()
    val errorConnection = MutableSharedFlow<String>()


    private val handler = CoroutineExceptionHandler { _, exception ->
        errorProcess(exception)
    }
    val vmScope = viewModelScope + handler + Dispatchers.IO

    private fun errorProcess(throwable: Throwable) {
        Log.d("error_base_vm", "$throwable")

        // TODO buyodayam shared flow buniyam blmiman qanaqa ishlatkan to'rirligini emit qlishga launch qlish kereyakan
        // bu joyi customizatasiya qlasan o'zinga mos qb throwable qo'shib keturasan response errorla qb
        when (throwable) {
            is UnknownHostException -> {
                viewModelScope.launch {
                    errorConnection.emit("Internet yoki Server bilan aloqa yo'q")
                }
            }
            is HttpException -> {
                when (throwable.code()) {
                    401, 403 -> {

                    }
                    400 -> {

                    }
                }

            }
            else -> {
                viewModelScope.launch {
                    errorMsg.emit("${throwable.message}")
                }
            }
        }
    }
}