package com.componentes.asab_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.componentes.asab_app.R
import com.componentes.asab_app.components.ButtonNavComponent
import com.componentes.asab_app.components.SearchComponent
import com.componentes.asab_app.navigation.Screen

@Composable
fun MainScreen(navController: NavController){

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 35.dp, vertical = 50.dp)
    ) {
        Column {
            ButtonNavComponent(stringResource(id = R.string.save_lyrics), navController, Screen.SaveSong.route)
            Spacer(modifier = Modifier.height(40.dp))
            ButtonNavComponent(stringResource(id = R.string.training_maraca), navController, Screen.Maraca.route)
            Spacer(modifier = Modifier.height(40.dp))
            ButtonNavComponent(stringResource(id = R.string.training_drum), navController, Screen.Drum.route)
            Spacer(modifier = Modifier.height(50.dp))
            SearchComponent(navController, Screen.Detail)
        }
    }

}
