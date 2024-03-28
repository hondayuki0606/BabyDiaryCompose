package com.example.babydiarycompose.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.babydiarycompose.R

enum class MenuOptions(
    @StringRes val title: Int,
    @DrawableRes val activeIcon: Int,
    @DrawableRes val inactiveIcon: Int,
    val route: String
) {
    HOME(
        R.string.home,  // home
//        R.drawable.ic_home_solid,
//        R.drawable.ic_home_outlined,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background,
        "home"
    ),
    COMPONENTS(
        R.string.components,  // components
//        R.drawable.ic_template_solid,
//        R.drawable.ic_template_outlined,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background,
        "components"
    ),
    ARTICLES(
        R.string.articles,  // articles
//        R.drawable.ic_document_text_solid,
//        R.drawable.ic_document_text_outlined,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background,
        "articles"
    ),
    SETTINGS(
        R.string.settings,  // settings
//        R.drawable.ic_cog_solid,
//        R.drawable.ic_cog_outlined,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background,
        "settings"
    ),
    WEB(
        R.string.settings,  // settings
//        R.drawable.ic_cog_solid,
//        R.drawable.ic_cog_outlined,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background,
        "web"
    ),
}