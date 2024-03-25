package com.componentes.asab_app

import android.content.Context
import android.content.Intent
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.componentes.asab_app.ui.theme.Asab_appTheme

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
                    //Greeting(data)
                    MyContent(this@MainActivity)

                }
            }
        }
    }

}





@Composable
fun MyContent(con: Context) {
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        FloatingButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),con
        )
    }
}


@Composable
fun FloatingButton(modifier: Modifier = Modifier, con: Context){
    FloatingActionButton(
        onClick = {
                val intent = Intent(con, SaveSongActivity::class.java)
                con.startActivity(intent)
        },
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "",
            tint = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    //MyContent()
}