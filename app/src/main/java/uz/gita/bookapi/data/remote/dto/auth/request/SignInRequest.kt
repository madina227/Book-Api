package uz.gita.bookapi.data.remote.dto.auth.request

data class SignInRequest(
    val phone: String,
    val password: String
)
