package ru.kykapek.starwarswiki.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import ru.kykapek.starwarswiki.utils.Resource
import ru.kykapek.starwarswiki.utils.SealedResource

open class ApiCall {

    suspend fun <T> safeApiCall(apiCall: suspend () -> T): SealedResource<T> {
        return withContext(Dispatchers.IO) {
            try {
                SealedResource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        SealedResource.Failure(throwable.response()?.errorBody().toString(), null)
                    }
                    else -> {
                        SealedResource.Failure(throwable.localizedMessage, null)
                    }
                }
            }
        }
    }


}