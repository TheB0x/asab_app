package com.componentes.asab_app.navigation


const val DETAIL_ARGUMENT_KEY = "name"
sealed class Screen(val route: String) {
    object Home: Screen("home_screen")
    object SaveSong: Screen("save_song_screen")
    object Maraca: Screen("maraca_screen")
    object Drum: Screen("drum_screen")
    object Detail: Screen("detail_screen/{$DETAIL_ARGUMENT_KEY}"){
        fun passkeyValue(name: String?): String{
            return "detail_screen/$name"
        }
    }

}