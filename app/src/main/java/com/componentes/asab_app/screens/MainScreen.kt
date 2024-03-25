package com.componentes.asab_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.componentes.asab_app.R
import com.componentes.asab_app.components.ButtonComponent
import com.componentes.asab_app.components.SearchComponent

@Composable
fun MainScreen(){

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(35.dp)
    ) {
        Column {
            ButtonComponent(stringResource(id = R.string.save_lyrics))
            Spacer(modifier = Modifier.height(40.dp))
            ButtonComponent(stringResource(id = R.string.training_maraca))
            Spacer(modifier = Modifier.height(40.dp))
            ButtonComponent(stringResource(id = R.string.training_tambor))
            Spacer(modifier = Modifier.height(50.dp))
            SearchComponent()
        }

    }

}