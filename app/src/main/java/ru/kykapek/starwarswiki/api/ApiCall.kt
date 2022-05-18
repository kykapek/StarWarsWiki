package ru.kykapek.starwarswiki.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import ru.kykapek.starwarswiki.utils.Resource

open class ApiCall {
    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        Resource.Failure(throwable.response()?.errorBody().toString(), null)
                    }
                    else -> {
                        Resource.Failure(throwable.localizedMessage, null)
                    }
                }
            }
        }
    }
}