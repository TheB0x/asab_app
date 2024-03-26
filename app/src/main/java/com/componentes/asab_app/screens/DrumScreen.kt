package com.componentes.asab_app.screens

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import com.componentes.asab_app.R
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.componentes.asab_app.components.ButtonNavComponent
import com.componentes.asab_app.components.ButtonSaveComponent
import com.componentes.asab_app.navigation.Screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.componentes.asab_app.ui.theme.Primary

@Composable
fun DrumScreen(navController: NavController){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ){
        instructions(navController)
    }
}
@Composable
private fun instructions(
    navController: NavController
){
    var startTraining by remember { mutableStateOf(true) }
    Column {
        if (startTraining) {
            Text(text = "Drum Screen")

            ButtonSaveComponent(stringResource(R.string.start_drum), navController) {
                startTraining = !startTraining

            }

        } else {
            touchBox()
        }
        Spacer(modifier = Modifier.height(25.dp))

        ButtonNavComponent(stringResource(R.string.back), navController, Screen.Home.route)
    }
}

@Composable
fun touchBox(){
    val context = LocalContext.current
    val snare: MediaPlayer = MediaPlayer.create(context, R.raw.snare)
    Box(
        modifier = Modifier
            .height(750.dp)
            .background(color = Primary)
            .fillMaxWidth()
            .clickable { snare.start() }
    )
}