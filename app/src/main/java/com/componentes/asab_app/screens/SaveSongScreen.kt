package com.componentes.asab_app.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.componentes.asab_app.R
import com.componentes.asab_app.components.ButtonNavComponent
import com.componentes.asab_app.components.ButtonSaveComponent
import com.componentes.asab_app.data.cto.SongCTO
import com.componentes.asab_app.data.dto.SongDTO
import com.componentes.asab_app.navigation.Screen

@Composable
fun SaveFormView(navController: NavController){
    var name by remember { mutableStateOf("") }
    var lyrics by remember { mutableStateOf("") }
    var id by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current


    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Nueva Cancion",
            style = TextStyle(fontSize = 24.sp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        TextField(
            value = name,
            onValueChange = {name = it},
            label = { Text(text = "Ingrese el nombre de la Cancion") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })

        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = lyrics,
            onValueChange = {lyrics = it},
            label = { Text(text = "Ingrese la letra de la Cancion") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
        )
        Spacer(modifier = Modifier.height(25.dp))
        Column {
            /*Button(
                onClick = {
                    val song = SongDTO(id, name, lyrics)
                    if(SongCTO().newSong(song)){
                        Toast.makeText(
                            context,
                            "Cancion Guardada",
                            Toast.LENGTH_SHORT
                        ).show()
                    }else{
                        Toast.makeText(
                            context,
                            "Error al Guardar la cancion",
                            Toast.LENGTH_SHORT
                        ).show()
                        name = ""
                        lyrics = ""
                    }
                }
            ) {
                Text(text = stringResource(R.string.save))
            }*/
            ButtonSaveComponent(
                stringResource(R.string.save),
                navController = navController
            ) {
                // Valida que los campos tengan datos
                if(name.isNotEmpty() && lyrics.isNotEmpty()){

                    // Crea el objeto Song
                    val song = SongDTO(id, name, lyrics)

                    // Crea el registro y la función retorna un Boolean. Registro guardado = true
                    if (SongCTO().newSong(song)) {
                        Toast.makeText(
                            context,
                            "Cancion Guardada",
                            Toast.LENGTH_SHORT
                        ).show()

                        name = ""
                        lyrics = ""

                        // Regresa a la vista principal
                        navController.navigate(route = Screen.Home.route ){
                            popUpTo(Screen.Home.route){
                                inclusive = true
                            }
                        }
                    } else {
                        Toast.makeText(
                            context,
                            "Error al Guardar la cancion",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }else{
                    Toast.makeText(
                        context,
                        "Complete todos los campos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            Spacer(Modifier.height(40.dp))
            ButtonNavComponent(
                stringResource(R.string.back),
                navController = navController,
                Screen.Home.route
            )
        }
    }
}