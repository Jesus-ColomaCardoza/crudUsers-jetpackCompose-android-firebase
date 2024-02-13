package com.fiel.note.ui.domain.repository

sealed class UserResult<T>(
    val data: T? = null,
    val message: String? = null
){
    class Success<T>(data: T?) : UserResult<T>(data)
    class Error<T>(message: String?, data: T? = null) : UserResult<T>(data, message)
    class Loading<T>(data: T? = null) : UserResult<T>(data)
}
