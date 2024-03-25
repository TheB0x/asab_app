package com.componentes.asab_app.ui.theme

import com.componentes.asab_app.data.dto.SongDTO
import com.componentes.asab_app.data.cto.SongCTO

class Main {

}

fun main(){
    val songCTO = SongCTO()
    val songDTO = SongDTO(
        "",
        "Where is my mind",
        "Lorem Ipsum"
    )



    println(songCTO.newSong(songDTO))


}