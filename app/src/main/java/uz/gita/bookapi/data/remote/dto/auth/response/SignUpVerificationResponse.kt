package uz.gita.bookapi.data.remote.dto.auth.response

data class SignUpVerificationResponse(
    val accessToken: String,
    val refreshToken: String
)
