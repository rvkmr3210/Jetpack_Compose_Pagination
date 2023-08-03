package com.rrm.rvkmr.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rrm.rvkmr.ui.screens.UsersList
import com.rrm.rvkmr.ui.screens.UserDetailsScreen

@Composable
fun UserNavGraph(navController: NavHostController, startDestination: String) {
    NavHost(
        navController,
        startDestination = startDestination
    ) {

        composable(NavScreen.UsersList.route) {
            UsersList(navController = navController)
        }

        composable(
            NavScreen.UserDetails.route,
            arguments = listOf(navArgument("userId") {
                type = NavType.StringType
            })
        ) {
            val userId = it.arguments?.getString("userId")
//            val userDetails = userDetailsString?.fromJson(UserModel::class.java)

            UserDetailsScreen(
                userId,
                onBackPressed = {
                    navController.navigateUp()
                })
        }
    }
}

sealed class NavScreen(val route: String) {

    object UsersList : NavScreen("users_list")
    object UserDetails : NavScreen("user_details/{userId}") {
        fun passUserDetails(userId: String?): String = "user_details/${userId}"

    }
}