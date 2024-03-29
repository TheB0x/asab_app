package com.componentes.asab_app.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.componentes.asab_app.R
import com.componentes.asab_app.components.ButtonNavComponent
import com.componentes.asab_app.components.ButtonSaveComponent
import com.componentes.asab_app.data.cto.SongCTO
import com.componentes.asab_app.navigation.Screen
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun DetailScreen(navController: NavController, keyParam : String){
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .padding(35.dp)
            .fillMaxWidth()
    ){
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(text = keyParam, fontWeight = FontWeight.Bold, fontSize = 24.sp)
            Spacer(modifier = Modifier.padding(vertical = 35.dp))
            lyrics(keyParam)
            Spacer(modifier = Modifier.padding(vertical = 35.dp))
            ButtonSaveComponent(stringResource(id = R.string.delete), navController){

                if (SongCTO().deleteSong(keyParam)){
                    Toast.makeText(context, "Cancion eliminada con Exito",Toast.LENGTH_SHORT).show()
                    navController.navigate(route = Screen.Home.route){
                        popUpTo(Screen.Home.route){
                            inclusive = true
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.padding(vertical = 25.dp))
            ButtonNavComponent(stringResource(id = R.string.back), navController, Screen.Home.route)
        }
    }

}


@Composable
fun lyrics(keyParam: String){

    val data = remember { mutableStateListOf<String>() }

    LaunchedEffect(Unit) {
        SongCTO().getDetailData(data, keyParam)
    }

    for (doc in data){
        Text(doc)
    }
}

