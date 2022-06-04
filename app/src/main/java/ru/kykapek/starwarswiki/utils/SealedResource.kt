package ru.kykapek.starwarswiki.utils

sealed class SealedResource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : SealedResource<T>(data)
    class Loading<T>(data: T? = null) : SealedResource<T>(data)
    class Failure<T>(message: String, data: T? = null) : SealedResource<T>(data, message)
    class Empty<Unit> : SealedResource<Unit>()
}
