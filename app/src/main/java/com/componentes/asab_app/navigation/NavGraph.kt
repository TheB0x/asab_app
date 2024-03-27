package com.componentes.asab_app.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
    }
}