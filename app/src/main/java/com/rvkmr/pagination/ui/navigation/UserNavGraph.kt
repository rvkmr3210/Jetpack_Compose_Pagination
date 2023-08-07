package com.rvkmr.pagination.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rvkmr.pagination.ui.screens.UsersList
import com.rvkmr.pagination.ui.screens.UserDetailsScreen
import com.rvkmr.pagination.ui.screens.UserReposScreen

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
            val userLogin = it.arguments?.getString("userId")

            UserDetailsScreen(
                userLogin,
                onBackPressed = {
                    navController.navigateUp()
                },
                onReposClick = {
                    navController.navigate(NavScreen.UserRepos.passUserDetails(userLogin))
                })
        }

        composable(NavScreen.UserRepos.route) {
            val userLogin = it.arguments?.getString("userLogin")
            UserReposScreen(userLogin, onBackPressed = { navController.navigateUp() })
        }
    }
}

sealed class NavScreen(val route: String) {
    object UsersList : NavScreen("users_list")
    object UserDetails : NavScreen("user_details/{userId}") {
        fun passUserDetails(userId: String?): String = "user_details/${userId}"
    }

    object UserRepos : NavScreen("user_repos/{userLogin}") {
        fun passUserDetails(userLogin: String?): String = "user_repos/${userLogin}"
    }
}