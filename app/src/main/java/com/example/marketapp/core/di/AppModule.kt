package com.example.marketapp.core.di

import android.content.Context
import com.example.marketapp.R
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
import com.example.marketapp.features.auth.domain.usecases.*
import com.example.marketapp.features.auth.infrastructure.api.AuthApi
import com.example.marketapp.features.auth.infrastructure.database.user_info_shared_pref.UserInfoSharedPrefImpl
import com.example.marketapp.features.home.data.data_source.remote.HomeRemoteDataSourceImpl
import com.example.marketapp.features.home.data.repo.HomeRepoImpl
import com.example.marketapp.features.home.domain.usecases.HomeUseCase
import com.example.marketapp.features.home.infrastructure.api.HomeApi
import com.example.marketapp.features.notification.data.data_source.remote.NotificationRemoteDataSource
import com.example.marketapp.features.notification.data.data_source.remote.NotificationRemoteDataSourceImpl
import com.example.marketapp.features.notification.data.repo.NotificationRepoImpl
import com.example.marketapp.features.notification.domain.usecase.GetAllNotificationsUseCase
import com.example.marketapp.features.notification.domain.usecase.GetNotificationUseCase
import com.example.marketapp.features.notification.domain.usecase.GetNotificationsCountUseCase
import com.example.marketapp.features.notification.infrastructure.api.NotificationApi
import com.example.marketapp.features.order.data.data_source.remote.OrderRemoteDataSourceImpl
import com.example.marketapp.features.order.data.repo.OrderRepoImpl
import com.example.marketapp.features.order.domain.usecases.*
import com.example.marketapp.features.order.domain.usecases.order.*
import com.example.marketapp.features.order.domain.usecases.places.GetDirectionsUseCase
import com.example.marketapp.features.order.domain.usecases.places.GetPlaceLatLongUseCase
import com.example.marketapp.features.order.domain.usecases.places.SearchPlacesUseCase
import com.example.marketapp.features.order.infrastructure.api.OrderApi
import com.example.marketapp.features.order.infrastructure.services.PlacesService
import com.example.marketapp.features.profile.data.data_source.remote.ProfileRemoteDataSourceImpl
import com.example.marketapp.features.profile.data.repo.ProfileRepoImpl
import com.example.marketapp.features.profile.domain.usecase.UpdatePhoneNumberStep2Usecase
import com.example.marketapp.features.profile.domain.usecase.UpdatePhoneNumberUsecase
import com.example.marketapp.features.profile.domain.usecase.UpdateProfileNameAndImageUsecase
import com.example.marketapp.features.profile.infrastructure.api.ProfileApi
import com.example.marketapp.features.wallet.data.data_source.remote.WalletRemoteDataSource
import com.example.marketapp.features.wallet.data.data_source.remote.WalletRemoteDataSourceImpl
import com.example.marketapp.features.wallet.data.repo.WalletRepoImpl
import com.example.marketapp.features.wallet.domain.usecase.ChargeBalanceUseCase
import com.example.marketapp.features.wallet.domain.usecase.GetBalanceUseCase
import com.example.marketapp.features.wallet.infrastructure.api.WalletApi
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.gson.GsonBuilder
import com.google.maps.GeoApiContext
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Apis
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
    fun providePlacesClient(@ApplicationContext context: Context) : PlacesClient {
        // Initialize the SDK
        Places.initialize(context, "AIzaSyBlRyjrVDFE3Ry_wivw70bqbH6VYccL9n0")

        // Create a new PlacesClient instance
        return Places.createClient(context)
    }

    @Provides
    @Singleton
    fun provideHomeApi() : HomeApi {
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
            .create(HomeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOrderApi() : OrderApi {
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
            .create(OrderApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProfileApi() : ProfileApi {
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
            .create(ProfileApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNotificationApi() : NotificationApi {
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
            .create(NotificationApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWalletApi() : WalletApi {
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
            .create(WalletApi::class.java)
    }


    // Services
    @Provides
    @Singleton
    fun provideNetworkService() : NetworkServiceImpl{
        return NetworkServiceImpl()
    }

    @Provides
    @Singleton
    fun providePlacesService(
        client : PlacesClient,
        directionsClient : GeoApiContext,
        @ApplicationContext context: Context
    ) : PlacesService{
        return PlacesService(
            client,
            directionsClient,
            context,
        )
    }

    @Provides
    @Singleton
    fun provideDirectionsService() : GeoApiContext{
        return GeoApiContext.Builder().apiKey("AIzaSyBlRyjrVDFE3Ry_wivw70bqbH6VYccL9n0").build()
    }

    @Singleton
    @Provides
    fun provideSignWithGoogleService(@ApplicationContext context: Context) : GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken(R.string.gcp_id.toString())
            .requestId()
            .requestProfile()
            .build()
        return GoogleSignIn.getClient(context, gso)
    }


    //  Remote Data Source
    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(api : AuthApi) : AuthRemoteDataSourceImpl {
        return AuthRemoteDataSourceImpl(api)
    }

    fun provideHomeRemoteDataSource(api : HomeApi) : HomeRemoteDataSourceImpl {
        return HomeRemoteDataSourceImpl(api)
    }

    fun provideOrderRemoteDataSource(api : OrderApi) : OrderRemoteDataSourceImpl {
        return OrderRemoteDataSourceImpl(api)
    }

    fun provideProfileRemoteDataSource(api : ProfileApi) : ProfileRemoteDataSourceImpl {
        return ProfileRemoteDataSourceImpl(api)
    }

    fun provideNotificationRemoteDataSource(api : NotificationApi) : NotificationRemoteDataSourceImpl {
        return NotificationRemoteDataSourceImpl(api)
    }

    fun provideWalletRemoteDataSource(api : WalletApi) : WalletRemoteDataSourceImpl {
        return WalletRemoteDataSourceImpl(api)
    }



    // Local Data Source
    @Provides
    @Singleton
    fun provideUserInfoSharedPref (
    ) : UserInfoSharedPrefImpl {
        return UserInfoSharedPrefImpl()
    }


    // Repos
    @Provides
    @Singleton
    fun provideAuthRepo(
        networkService : NetworkServiceImpl,
        sharedPref: UserInfoSharedPrefImpl,
        remoteDataSource : AuthRemoteDataSourceImpl
    ) : AuthRepoImpl {
        return AuthRepoImpl(
            networkService = networkService,
            sharedPref = sharedPref,
            remoteDataSource = remoteDataSource
        )
    }

    @Provides
    @Singleton
    fun provideOrderRepo(
        networkService : NetworkServiceImpl,
        remoteDataSource : OrderRemoteDataSourceImpl
    ) : OrderRepoImpl {
        return OrderRepoImpl(
            networkService = networkService,
            remoteDataSource = remoteDataSource
        )
    }
    fun provideHomeRepo(
        networkService : NetworkServiceImpl,
        remoteDataSource : HomeRemoteDataSourceImpl
    ) : HomeRepoImpl {
        return HomeRepoImpl(
            networkService = networkService,
            remoteDataSource = remoteDataSource
        )
    }

    fun provideProfileRepo(
        networkService : NetworkServiceImpl,
        remoteDataSource : ProfileRemoteDataSourceImpl
    ) : ProfileRepoImpl {
        return ProfileRepoImpl(
            networkService = networkService,
            remoteDataSource = remoteDataSource
        )
    }

    fun provideNotificationRepo(
        networkService : NetworkServiceImpl,
        remoteDataSource : NotificationRemoteDataSourceImpl
    ) : NotificationRepoImpl {
        return NotificationRepoImpl(
            networkService = networkService,
            remoteDataSource = remoteDataSource
        )
    }

    fun provideWalletRepo(
        networkService : NetworkServiceImpl,
        remoteDataSource : WalletRemoteDataSourceImpl
    ) : WalletRepoImpl {
        return WalletRepoImpl(
            networkService = networkService,
            remoteDataSource = remoteDataSource
        )
    }


    // Use cases
    @Provides
    @Singleton
    fun provideSaveUserInfoUseCase(repo : AuthRepoImpl,) : SaveUserInfoUseCase {
        return SaveUserInfoUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideGetUserInfoUseCase(repo : AuthRepoImpl) : GetUserInfoUseCase {
        return GetUserInfoUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideDeleteUserInfoUseCasee(repo : AuthRepoImpl) : DeleteUserInfoUseCase {
        return DeleteUserInfoUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(repo : AuthRepoImpl,saveUserInfoUseCase: SaveUserInfoUseCase) : LoginUseCase {
        return LoginUseCase(repo,saveUserInfoUseCase)
    }

    @Provides
    @Singleton
    fun provideRegisterUseCase(repo : AuthRepoImpl) : RegisterUseCase {
        return RegisterUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideActivateAccountUseCase(repo : AuthRepoImpl,saveUserInfoUseCase: SaveUserInfoUseCase) : ConfirmCodeUseCase {
        return ConfirmCodeUseCase(repo,saveUserInfoUseCase)
    }


    @Provides
    @Singleton
    fun provideResetPasswordUseCase(repo : AuthRepoImpl) : ResetPasswordUseCase {
        return ResetPasswordUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideSendCodeToPhoneUseCase(repo : AuthRepoImpl) : SendCodeToPhoneUseCase {
        return SendCodeToPhoneUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideValidateSmsCodeUseCase(repo : AuthRepoImpl) : CheckCodeSentUseCase {
        return CheckCodeSentUseCase(repo)
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
    fun provideHomeUseCase(repo: HomeRepoImpl) : HomeUseCase {
        return HomeUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideMakeOrderStep1UseCase(repo: OrderRepoImpl) : MakeOrderStep1UseCase {
        return MakeOrderStep1UseCase(repo)
    }

    @Provides
    @Singleton
    fun provideMakeOrderStep2UseCase(repo: OrderRepoImpl) : MakeOrderStep2UseCase {
        return MakeOrderStep2UseCase(repo)
    }

    @Provides
    @Singleton
    fun provideOrderDetailsUseCase(repo: OrderRepoImpl) : OrderDetailsUseCase {
        return OrderDetailsUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideOrderUseCase(repo: OrderRepoImpl) : OrderUseCase {
        return OrderUseCase(repo)
    }
    @Provides
    @Singleton
    fun provideSearchPlacesUseCase(service: PlacesService) : SearchPlacesUseCase {
        return SearchPlacesUseCase(service)
    }
    @Provides
    @Singleton
    fun provideGetPlaceLatLongUseCase(service: PlacesService) : GetPlaceLatLongUseCase {
        return GetPlaceLatLongUseCase(service)
    }

    @Provides
    @Singleton
    fun provideGetDirectionsUseCase(service: PlacesService) : GetDirectionsUseCase {
        return GetDirectionsUseCase(service)
    }


    @Provides
    @Singleton
    fun provideCancelOrderUseCase(repo: OrderRepoImpl) : CancelOrderUseCase {
        return CancelOrderUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideUpdatePhoneNumberUseCase(repo: ProfileRepoImpl) : UpdatePhoneNumberUsecase {
        return UpdatePhoneNumberUsecase(repo)
    }

    @Provides
    @Singleton
    fun provideUpdatePhoneNumberStep2UseCase(repo: ProfileRepoImpl) : UpdatePhoneNumberStep2Usecase {
        return UpdatePhoneNumberStep2Usecase(repo)
    }

    @Provides
    @Singleton
    fun provideUpdateProfileNameAndImageUseCase(repo: ProfileRepoImpl) : UpdateProfileNameAndImageUsecase {
        return UpdateProfileNameAndImageUsecase(repo)
    }

    @Provides
    @Singleton
    fun provideGetAllNotificationsUseCase(repo: NotificationRepoImpl) : GetAllNotificationsUseCase {
        return GetAllNotificationsUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideGetNotificationUseCase(repo: NotificationRepoImpl) : GetNotificationUseCase {
        return GetNotificationUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideGetNotificationCountUseCase(repo: NotificationRepoImpl) : GetNotificationsCountUseCase {
        return GetNotificationsCountUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideGetWalletUseCase(repo: WalletRepoImpl) : GetBalanceUseCase {
        return GetBalanceUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideChargeBalanceUseCase(repo: WalletRepoImpl) : ChargeBalanceUseCase {
        return ChargeBalanceUseCase(repo)
    }



    // Utils
    @Provides
    @Singleton
    fun provideValidator() : Validator {
        return Validator()
    }




}