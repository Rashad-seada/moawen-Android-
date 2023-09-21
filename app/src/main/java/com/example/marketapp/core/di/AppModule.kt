package com.example.marketapp.core.di

import com.example.marketapp.core.infrastructure.Api.ProductApi
import com.example.marketapp.core.util.Consts.BASE_URL
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
    fun provideProductApi() : ProductApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
//            .addCallAdapterFactory(
//                CoroutineCallAdapterFactory()
//            )
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(ProductApi::class.java)
    }


}