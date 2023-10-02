package com.example.marketapp.features.home.domain.usecases

import android.content.Context
import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.home.data.entities.home.HomeResponse
import com.example.marketapp.features.home.data.repo.HomeRepoImpl
import javax.inject.Inject


class HomeUseCase @Inject constructor(
    val repo: HomeRepoImpl,
    ) {

    suspend operator fun invoke(
        token: String,
        context: Context
    ): Resource<HomeResponse> {

        return repo.home(
            token,
            context
        )

    }


}