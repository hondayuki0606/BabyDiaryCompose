package com.example.babydiarycompose.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.babydiarycompose.R

enum class Screen(
    val index: Int,
    @StringRes val title: Int = 0,
    @DrawableRes val activeIcon: Int = 0,
    @DrawableRes val inactiveIcon: Int = 0,
    val route: String
) {
    RECORD(
        0,
        R.string.home,  // home
//        R.drawable.ic_home_solid,
//        R.drawable.ic_home_outlined,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background,
        "record"
    ),
    SUMMARY(
        1,
        R.string.components,  // components
//        R.drawable.ic_template_solid,
//        R.drawable.ic_template_outlined,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background,
        "summary"
    ),
    GROW(
        2,
        R.string.articles,  // articles
//        R.drawable.ic_document_text_solid,
//        R.drawable.ic_document_text_outlined,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background,
        "grow"
    ),
    MENU(
        3,
        R.string.settings,  // settings
//        R.drawable.ic_cog_solid,
//        R.drawable.ic_cog_outlined,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background,
        "menu"
    ),
    ACCOUNT(
        99,
        R.string.settings,  // settings
//        R.drawable.ic_cog_solid,
//        R.drawable.ic_cog_outlined,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background,
        "account"
    ),
    SETTINGS(
        99,
        R.string.settings,  // settings
//        R.drawable.ic_cog_solid,
//        R.drawable.ic_cog_outlined,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background,
        "settings"
    ),
    WEB(
        99,
        R.string.settings,  // settings
//        R.drawable.ic_cog_solid,
//        R.drawable.ic_cog_outlined,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background,
        "web"
    ),
}