package com.loginapi

import android.annotation.SuppressLint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.FieldMap
import java.io.IOException

class UserRepository {
    private var apiService: LoginInterface? = null
    private var apiServiceWithToken: LoginInterface? = null

    init {
        apiService = RetrofitApiGenerator().retrofitClientWithoutToken().create(LoginInterface::class.java)
        apiServiceWithToken = RetrofitApiGenerator().retrofitClient().create(LoginInterface::class.java)
    }
    @SuppressLint("SuspiciousIndentation")
    suspend fun logUser(
        @FieldMap params: MutableMap<String?, String?>?
    ): ResultResponse {
        return try {
            val response = withContext(Dispatchers.IO) {
                apiService?.loginUser(params)?.execute()
            }
            if (response?.isSuccessful == true && response.body() != null) {
                ResultResponse.Success(response.body())
            }  else {
                when (response?.code()) {
                    403 -> ResultResponse.HttpErrors.ResourceForbidden(response.message())
                    404 -> ResultResponse.HttpErrors.ResourceNotFound(response.message())
                    500 -> ResultResponse.HttpErrors.InternalServerError(response.message())
                    502 -> ResultResponse.HttpErrors.BadGateWay(response.message())
                    301 -> ResultResponse.HttpErrors.ResourceRemoved(response.message())
                    302 -> ResultResponse.HttpErrors.RemovedResourceFound(response.message())
                    else -> ResultResponse.Error(response?.message() ?: "Unknown Error")
                }
            }
        } catch (error: IOException) {
            ResultResponse.NetworkException(error.message ?: "Network Exception")
        }
    }
}