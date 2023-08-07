package com.rvkmr.pagination.extensions

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import com.google.gson.Gson



fun NavOptionsBuilder.popUpToTop(navController: NavController) {
    popUpTo(navController.currentBackStackEntry?.destination?.route ?: return) {
        inclusive =  true
    }
}


fun <A> String.fromJson(type: Class<A>): A {
    return Gson().fromJson(this, type)
}

fun <A> A.toJson(): String? {
    return Gson().toJson(this)
}
