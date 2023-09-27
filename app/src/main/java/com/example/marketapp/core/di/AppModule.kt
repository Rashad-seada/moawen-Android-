package com.example.marketapp.core.di

import com.example.marketapp.core.infrastructure.services.NetworkServiceImpl
import com.example.marketapp.core.util.Consts.BASE_URL
import com.example.marketapp.core.util.usecase.ValidateEmailUseCase
import com.example.marketapp.core.util.usecase.ValidatePasswordUseCase
import com.example.marketapp.core.util.usecase.ValidatePhoneUseCase
import com.example.marketapp.core.util.validator.Validator
import com.example.marketapp.features.auth.data.data_source.remote.AuthRemoteDataSourceImpl
import com.example.marketapp.features.auth.data.repo.AuthRepoImpl
import com.example.marketapp.features.auth.domain.usecases.LoginUseCase
import com.example.marketapp.features.auth.infrastructure.Api.AuthApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthApi() : AuthApi {
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkService() : NetworkServiceImpl{
        return NetworkServiceImpl()
    }

    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(api : AuthApi) : AuthRemoteDataSourceImpl {
        return AuthRemoteDataSourceImpl(api)
    }

    @Provides
    @Singleton
    fun provideAuthRepo(
        networkService : NetworkServiceImpl,
        remoteDataSource : AuthRemoteDataSourceImpl
    ) : AuthRepoImpl {
        return AuthRepoImpl(
            networkService = networkService,
            remoteDataSource = remoteDataSource
        )
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(repo : AuthRepoImpl) : LoginUseCase {
        return LoginUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideValidateEmailUseCase(validator: Validator) : ValidateEmailUseCase {
        return ValidateEmailUseCase(validator)
    }

    @Provides
    @Singleton
    fun provideValidatePhoneUseCase(validator: Validator) : ValidatePhoneUseCase {
        return ValidatePhoneUseCase(validator)
    }

    @Provides
    @Singleton
    fun provideValidatePasswordUseCase() : ValidatePasswordUseCase {
        return ValidatePasswordUseCase()
    }

    @Provides
    @Singleton
    fun provideValidator() : Validator {
        return Validator()
    }


}