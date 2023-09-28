package com.example.marketapp.core.di

import com.example.marketapp.core.infrastructure.services.NetworkServiceImpl
import com.example.marketapp.core.util.Consts.BASE_URL
import com.example.marketapp.core.util.usecase.ValidateEmailLocalUseCase
import com.example.marketapp.core.util.usecase.ValidatePasswordLocalUseCase
import com.example.marketapp.core.util.usecase.ValidatePasswordRepeatedLocalUseCase
import com.example.marketapp.core.util.usecase.ValidatePhoneLocalUseCase
import com.example.marketapp.core.util.usecase.ValidateUsernameLocalUseCase
import com.example.marketapp.core.util.validator.Validator
import com.example.marketapp.features.auth.data.data_source.remote.AuthRemoteDataSourceImpl
import com.example.marketapp.features.auth.data.repo.AuthRepoImpl
import com.example.marketapp.features.auth.domain.usecases.ActivateAccountUseCase
import com.example.marketapp.features.auth.domain.usecases.LoginUseCase
import com.example.marketapp.features.auth.domain.usecases.RegisterUseCase
import com.example.marketapp.features.auth.domain.usecases.ResetPasswordByEmailUseCase
import com.example.marketapp.features.auth.domain.usecases.ResetPasswordByPhoneUseCase
import com.example.marketapp.features.auth.domain.usecases.SendSmsUseCase
import com.example.marketapp.features.auth.domain.usecases.ValidateEmailUseCase
import com.example.marketapp.features.auth.domain.usecases.ValidatePhoneUseCase
import com.example.marketapp.features.auth.domain.usecases.ValidateSmsUseCase
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
    fun provideRegisterUseCase(repo : AuthRepoImpl) : RegisterUseCase {
        return RegisterUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideActivateAccountUseCase(repo : AuthRepoImpl) : ActivateAccountUseCase {
        return ActivateAccountUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideResetPasswordByEmailUseCase(repo : AuthRepoImpl) : ResetPasswordByEmailUseCase {
        return ResetPasswordByEmailUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideResetPasswordByPhoneUseCase(repo : AuthRepoImpl) : ResetPasswordByPhoneUseCase {
        return ResetPasswordByPhoneUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideSendSmsCodeUseCase(repo : AuthRepoImpl) : SendSmsUseCase {
        return SendSmsUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideValidateEmailUseCase(repo : AuthRepoImpl) : ValidateEmailUseCase {
        return ValidateEmailUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideValidatePhoneUseCase(repo : AuthRepoImpl) : ValidatePhoneUseCase {
        return ValidatePhoneUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideValidateSmsCodeUseCase(repo : AuthRepoImpl) : ValidateSmsUseCase {
        return ValidateSmsUseCase(repo)
    }


    @Provides
    @Singleton
    fun provideLocalValidateUsernameUseCase() : ValidateUsernameLocalUseCase {
        return ValidateUsernameLocalUseCase()
    }

    @Provides
    @Singleton
    fun provideLocalValidateEmailUseCase(validator: Validator) : ValidateEmailLocalUseCase {
        return ValidateEmailLocalUseCase(validator)
    }

    @Provides
    @Singleton
    fun provideLocalValidatePhoneUseCase(validator: Validator) : ValidatePhoneLocalUseCase {
        return ValidatePhoneLocalUseCase(validator)
    }

    @Provides
    @Singleton
    fun provideValidatePasswordUseCase() : ValidatePasswordLocalUseCase {
        return ValidatePasswordLocalUseCase()
    }

    @Provides
    @Singleton
    fun provideValidatePasswordRepeatedUseCase() : ValidatePasswordRepeatedLocalUseCase {
        return ValidatePasswordRepeatedLocalUseCase()
    }

    @Provides
    @Singleton
    fun provideValidator() : Validator {
        return Validator()
    }


}