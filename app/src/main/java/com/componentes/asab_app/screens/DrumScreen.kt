package com.componentes.asab_app.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import com.componentes.asab_app.R
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.componentes.asab_app.components.ButtonNavComponent
import com.componentes.asab_app.navigation.Screen

@Composable
fun DrumScreen(navController: NavController){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column {

            Text(text = "Drum Screen")
            ButtonNavComponent(stringResource(R.string.back), navController, Screen.Home.route)
        }
    }
}