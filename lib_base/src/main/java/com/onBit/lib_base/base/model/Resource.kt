package com.onBit.lib_base.base.model

import java.lang.Exception

sealed class Resource<T>(
    val data: T? = null,
    val e: Exception? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)

    class Failure<T>() : Resource<T>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Failure -> "Failure[exception=${e.toString()}]"
            is Loading<T> -> "Loading"
        }
    }

    fun isSuccess(): Boolean = this is Success
}