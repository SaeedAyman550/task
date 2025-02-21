package com.example.test.domain.utils


sealed class Resourse<T>(
    val data: T? = null,
    val message: String = "",
    val errorType: ErrorData? = null
) {
    class Success<T>(data: T) : Resourse<T>(data)
    class Error<T>(message: String="", errorType: ErrorData=ErrorData.UnKnownError) : Resourse<T>(null, message, errorType)
    class Loading<T> : Resourse<T>()
}
enum class ErrorData{
    NotFoundError,
    UnauthorizedError,
    UnKnownError
}
