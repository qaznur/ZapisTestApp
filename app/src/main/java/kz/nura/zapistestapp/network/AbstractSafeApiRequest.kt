package kz.nura.zapistestapp.network

import android.app.Application
import kz.nura.zapistestapp.NoInternetException
import kz.nura.zapistestapp.ServerException
import kz.nura.zapistestapp.utils.isInetOK
import retrofit2.Response

abstract class AbstractSafeApiRequest(private val application: Application) {

    suspend fun <T : Any> apiRequest(function: suspend () -> Response<T>): T? {
        if (isInetOK(application)) {
//            try {
                val response = function.invoke()
                if (response.isSuccessful) {
                    return response.body()
                } else {
                    val errorCode = response.code()
                    throw ServerException("Server error, code: $errorCode")
                }
//            }
//        catch (ex: Exception) {
//                if(ex is ServerException) {
//                    throw ServerException(ex.message!!)
//                }
//                throw UnknownException("Unknown error")
//            }
        } else {
            throw NoInternetException("No internet")
        }
    }

}