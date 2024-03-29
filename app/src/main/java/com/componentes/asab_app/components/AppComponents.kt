package com.componentes.asab_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.componentes.asab_app.data.cto.SongCTO
import com.componentes.asab_app.navigation.Screen
import com.componentes.asab_app.ui.theme.Primary
import com.componentes.asab_app.ui.theme.Roboto
import com.componentes.asab_app.ui.theme.Secondary


@Composable
fun ButtonNavComponent(value: String, navController: NavController, route: String){

    val data = remember { mutableStateListOf<String>() }
    LaunchedEffect(Unit) {
        SongCTO().getDataFromFirestore(data)
    }

    Button(
        onClick = {
            navController.navigate(route = route ){
                popUpTo(route){
                    inclusive = true
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ){
            textContent(value)
        }
    }
}

@Composable
fun ButtonSaveComponent(value: String, navController: NavController, onClick: ()->Unit){
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ){
            textContent(
                text = value,
            )
        }
    }
}


//Search Field without conection to database//

@Composable
fun SearchComponent(navController: NavController, route: Screen.Detail){
    /*


    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    var items = remember {
        //mutableStateListOf("Where is my mind", "Funky Town")
        data
    }

 */
    val context = LocalContext.current
    val data = remember { mutableStateListOf<String>() }
    LaunchedEffect(Unit) {
        SongCTO().getDataFromFirestore(data)
    }

    SearchableTextField(data) {
        //Navigate to detail
        itemName ->
        navController.navigate(route = route.passkeyValue(itemName)){
            popUpTo(route.passkeyValue(itemName)){
                inclusive = true
            }
        }

    }
}

@Composable
fun SearchableTextField(data: List<String>, onItemClick: (String) -> Unit) {
    var searchText by remember { mutableStateOf(TextFieldValue()) }
    var searchResults by remember { mutableStateOf(data) } // Inicialmente muestra todos los datos+

    Column{
        OutlinedTextField(
            value = searchText,
            onValueChange = {
                searchText = it
                searchResults = filterData(data, it.text)
            },
            shape = RoundedCornerShape(50.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
            },
            label = { textSpecs("Buscar") },
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn{
            items(searchResults) { item ->
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ){
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onItemClick(item) }
                    ) {
                        textSpecs(
                            text = item,
                            /*modifier = Modifier
                                //.padding(16.dp)
                                .clickable { onItemClick(item) }*/
                        )
                    }
                }
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose { }
    }
}

fun filterData(data: List<String>, query: String): List<String> {
    return if (query.isEmpty()) {
        data // Si la consulta está vacía, devuelve todos los datos
    } else {
        data.filter { it.contains(query, ignoreCase = true) }
    }
}


// Components for text
@Composable
fun textTitle(text: String){
    Text(
        text = text,
        style = TextStyle(
            fontFamily = Roboto,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontSize = 20.sp,
            letterSpacing = 0.sp,
            lineHeight = 27.sp
        ))
}

@Composable
fun textContent(text: String){
    Text(
        text = text,
        textAlign = TextAlign.Center,
        style = TextStyle(
            fontFamily = Roboto,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            fontSize = 16.sp,
            letterSpacing = 0.sp,
            lineHeight = 27.sp
        ))
}

@Composable
fun textSpecs(text: String){
    Text(
        text = text,
        style = TextStyle(
            fontFamily = Roboto,
            fontWeight = FontWeight.Light,
            fontStyle = FontStyle.Normal,
            fontSize = 12.sp,
            letterSpacing = 0.sp,
            lineHeight = 27.sp
        ))
}

