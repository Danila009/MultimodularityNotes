package com.example.core.core_database_domain.common

sealed class Response<T>(val data:T? = null, val message:String? = null){
    class Error<T>(message: String):Response<T>(message = message)
    class Loading<T>:Response<T>()
    class Success<T>(data: T):Response<T>(data = data)
}