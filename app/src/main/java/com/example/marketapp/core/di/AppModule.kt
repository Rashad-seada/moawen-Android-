package com.example.marketapp.core.di

import com.example.marketapp.core.infrastructure.services.NetworkServiceImpl
import com.example.marketapp.core.util.Consts.BASE_URL
import com.example.marketapp.features.auth.data.data_source.remote.AuthRemoteDataSourceImpl
import com.example.marketapp.features.auth.data.repo.AuthRepoImpl
import com.example.marketapp.features.auth.domain.usecases.LoginUseCase
import com.example.marketapp.features.auth.infrastructure.Api.AuthApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideAuthApi() : AuthApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(AuthApi::class.java)
    }

    fun provideNetworkService() : NetworkServiceImpl{
        return NetworkServiceImpl()
    }

    fun provideAuthRemoteDataSource(api : AuthApi) : AuthRemoteDataSourceImpl {
        return AuthRemoteDataSourceImpl(api)
    }

    fun provideAuthRepo(
        networkService : NetworkServiceImpl,
        remoteDataSource : AuthRemoteDataSourceImpl
    ) : AuthRepoImpl {
        return AuthRepoImpl(
            networkService = networkService,
            remoteDataSource = remoteDataSource
        )
    }

    fun provideLoginUseCase(repo : AuthRepoImpl) : LoginUseCase {
        return LoginUseCase(repo)
    }


}