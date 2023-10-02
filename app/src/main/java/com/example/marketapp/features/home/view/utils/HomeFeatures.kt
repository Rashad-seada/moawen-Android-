package com.example.marketapp.features.home.view.utils

import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.marketapp.R
import com.example.marketapp.destinations.DirectionDestination

sealed class HomeFeatures(
    val screen: DirectionDestination?,
    val icon: Int,
    val label: Int,
    val subText: Int,

    ) {
    @OptIn(ExperimentalMaterial3Api::class)
    object Exams : HomeFeatures(
        screen = null,
        icon = R.drawable.exam,
        label = R.string.exam,
        subText = R.string.exam_sub_text
    )

    object Books : HomeFeatures(
        screen = null,
        icon = R.drawable.books,
        label = R.string.books,
        subText = R.string.books_sub_text

    )

    object Tests : HomeFeatures(
        screen = null,
        icon = R.drawable.test,
        label = R.string.test,
        subText = R.string.test_sub_text

    )

    object OpenBook : HomeFeatures(
        screen = null,
        icon = R.drawable.open_book,
        label = R.string.open_book,
        subText = R.string.open_book_sub_text

    )

    object Resume : HomeFeatures(
        screen = null,
        icon = R.drawable.resume,
        label = R.string.resume,
        subText = R.string.resume_sub_text


    )

    object PersonalInfo : HomeFeatures(
        screen = null,
        icon = R.drawable.personal_information,
        label = R.string.personal_information,
        subText = R.string.personal_information_sub_text

    )

    object Read : HomeFeatures(
        screen = null,
        icon = R.drawable.read,
        label = R.string.read,
        subText = R.string.read_sub_text

    )

    object Study : HomeFeatures(
        screen = null,
        icon = R.drawable.study,
        label = R.string.study,
        subText = R.string.study_sub_text

    )

    object HomeWork : HomeFeatures(
        screen = null,
        icon = R.drawable.home_work,
        label = R.string.home_work,
        subText = R.string.home_work_sub_text

    )

    object Propose : HomeFeatures(
        screen = null,
        icon = R.drawable.propose,
        label = R.string.propose,
        subText = R.string.propose_sub_text

    )

    object LanguageArt : HomeFeatures(
        screen = null,
        icon = R.drawable.language_arts,
        label = R.string.language_arts,
        subText = R.string.language_arts_sub_text

    )

}

