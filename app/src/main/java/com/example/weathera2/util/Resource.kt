package com.example.weathera2.util

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> loading(data: T?): Resource<T> = Resource(Status.LOADING, data, null)
        fun <T> success(data: T?): Resource<T> = Resource(Status.SUCCESS, data, null)
        fun <T> error(data: T?, message: String): Resource<T> = Resource(Status.ERROR, data, message)
    }
}
