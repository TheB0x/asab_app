package com.componentes.asab_app.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home_screen")
    object SaveSong: Screen("save_song_screen")
    object Maraca: Screen("maraca_screen")
    object Drum: Screen("drum_screen")

}