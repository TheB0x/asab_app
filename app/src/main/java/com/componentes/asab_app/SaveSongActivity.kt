package com.componentes.asab_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.componentes.asab_app.screens.SaveFormView

class SaveSongActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SaveFormView()
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewForm() {
    SaveFormView()
}