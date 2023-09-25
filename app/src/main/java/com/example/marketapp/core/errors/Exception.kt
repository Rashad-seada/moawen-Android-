package com.example.marketapp.core.errors

class RemoteDataException(message: String?) : Exception(message)

class ServiceException(message: String?) : Exception(message)

class LocalDataException(message: String?) : Exception(message)

class InternalException(message: String?) : Exception(message)
