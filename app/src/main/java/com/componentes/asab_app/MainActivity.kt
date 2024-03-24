package com.componentes.asab_app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.componentes.asab_app.data.cto.SongCTO
import com.componentes.asab_app.data.dto.SongDTO
import com.componentes.asab_app.ui.theme.Asab_appTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    var data  = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Guardar un nuevo Registro
        /*
        val songCTO = SongCTO()
        val songDTO = SongDTO(
            "",
            "Where is my mind",
            "Lorem Ipsum"
        )


        val band = songCTO.newSong(songDTO)
        println(band)
        */


        setContent {
            Asab_appTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    //Greeting("Android!")
                    Greeting(data)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Asab_appTheme {
        Greeting("Android")
    }
}