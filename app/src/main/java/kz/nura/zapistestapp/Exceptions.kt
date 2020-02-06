package kz.nura.zapistestapp

import java.lang.Exception

class NoInternetException(message: String): Exception(message)
class ServerException(message: String): Exception(message)
class UnknownException(message: String): Exception(message)
