package com.componentes.asab_app.navigation

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.componentes.asab_app.screens.*

@Composable
fun SetupNavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(
            route = Screen.Home.route
        ){
            MainScreen(navController = navController)
        }

        composable(
            route = Screen.SaveSong.route
        ){
            SaveFormView(navController = navController)
        }

        composable(
            route = Screen.Maraca.route
        ){
            MaracaScreen(navController = navController)
        }

        composable(
            route = Screen.Drum.route
        ){
            DrumScreen(navController = navController)
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument(DETAIL_ARGUMENT_KEY){
                type = NavType.StringType
            })
        ){
            val keyParam = it.arguments!!.getString(DETAIL_ARGUMENT_KEY) ?: ""
            DetailScreen(navController = navController, keyParam = keyParam)
        }
    }
}