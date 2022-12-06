package uz.gita.bookapi.data.model

sealed interface ResultData<T> {
    class Success<T>(val data: T?) : ResultData<T>
    class Loading<T>(val isLoading: Boolean) : ResultData<T>
    class HasConnection<T>(val hasConnection: Boolean) : ResultData<T>
    class Fail<T>(val message: String) : ResultData<T>
}

/*
sealed class sorovimiza success loading hasconnection fail holatlariga
 */