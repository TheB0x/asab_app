package com.componentes.asab_app.screens

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import com.componentes.asab_app.R
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.componentes.asab_app.ui.theme.Primary

@Composable
fun MaracaScreen(navController: NavController){
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
            Text(
                text = "Maraca Screen",
                fontSize = 32.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(25.dp))
            ButtonSaveComponent(stringResource(R.string.start_maraca), navController) {
                startTraining = !startTraining
            }
        } else {
            ShakeDetectionScreen()
        }
        Spacer(modifier = Modifier.height(25.dp))
        ButtonNavComponent(stringResource(R.string.back), navController, Screen.Home.route)
    }
}

@Composable
fun ShakeDetectionScreen() {
    val context = LocalContext.current
    val sensorManager = remember { context.getSystemService(Context.SENSOR_SERVICE) as SensorManager }
    val soundPool = remember { createSoundPool() }
    val soundId = remember { soundPool.load(context, R.raw.maraca, 1) }
    var shakeLevel by remember { mutableStateOf(0) }

    DisposableEffect(Unit) {
        val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val listener = ShakeEventListener { level ->
            shakeLevel = level
            playSound(soundPool, soundId, shakeLevel)
        }
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_GAME)

        // Finalizar cuando el Composable es removido de la pantalla

        onDispose {
            sensorManager.unregisterListener(listener)
            soundPool.release()
        }
    }

    BackHandler {
        // Limpiar el Pool cuando se presiona el botón de regresar
        soundPool.release()
    }
    Spacer(modifier = Modifier.height(25.dp))
    Text(text = "Sacude tu telefono")
}


class ShakeEventListener(private val onShakeDetected: (Int) -> Unit) : SensorEventListener {
    private val shakeThreshold = 30f
    private var lastUpdate: Long = 0
    private var lastX = 0f
    private var lastY = 0f
    private var lastZ = 0f


    
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
    override fun onSensorChanged(event: SensorEvent) {
        val currentTime = System.currentTimeMillis()
        val timeDifference = currentTime - lastUpdate

        if (timeDifference > 200) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            val acceleration = Math.abs(x + y + z - lastX - lastY - lastZ) / timeDifference * 10000
            if (acceleration > shakeThreshold) {
                onShakeDetected((acceleration / shakeThreshold).toInt())
            }

            lastX = x
            lastY = y
            lastZ = z
            lastUpdate = currentTime
        }
    }
}

fun createSoundPool(): SoundPool {
    val audioAttributes = AudioAttributes.Builder()
        .setUsage(AudioAttributes.USAGE_GAME)
        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
        .build()
    return SoundPool.Builder()
        .setMaxStreams(1)
        .setAudioAttributes(audioAttributes)
        .build()
}

fun playSound(soundPool: SoundPool, soundId: Int, level: Int){
    val volume = when (level){
        in 1..3 -> 0.4f
        in 4..6 -> 1.0f
        else -> 2.0f
    }
    soundPool.play(soundId, volume, volume, 1, 0, 1f)
}
