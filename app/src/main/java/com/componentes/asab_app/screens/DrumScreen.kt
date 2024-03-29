package com.componentes.asab_app.screens

import android.media.AudioAttributes
import android.media.SoundPool
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.componentes.asab_app.components.textContent
import com.componentes.asab_app.components.textTitle
import com.componentes.asab_app.ui.theme.Primary
import com.componentes.asab_app.ui.theme.Secondary

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
    Column (Modifier.padding(horizontal = 35.dp)){
        if (startTraining) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 35.dp)
            ){
                textTitle(text = stringResource(R.string.title_drum))
                Spacer(modifier = Modifier.height(10.dp))
                textContent(text = stringResource(R.string.intructions_drum))
            }

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
    val snareBuilder = remember { createdSound() }
    val snareId = remember { snareBuilder.load(context, R.raw.snare, 1) }
    var color by remember {mutableStateOf(Primary)}
    Box(
        modifier = Modifier
            .height(750.dp)
            .background(color = color)
            .fillMaxWidth()
            .pointerInput(Unit){
                detectTapGestures(onTap = {
                    snareBuilder.play(snareId, 1f, 1f, 1, 0, 1f)
                    color = if (color == Primary) Secondary else Primary
                })
            }
    )
}

fun createdSound(): SoundPool {
    val attributes = AudioAttributes
        .Builder()
        .setUsage(AudioAttributes.USAGE_GAME)
        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
        .build()
    return SoundPool.Builder()
        .setMaxStreams(6)
        .setAudioAttributes(attributes)
        .build()

}