package com.componentes.asab_app.data.cto

import com.componentes.asab_app.data.config.FirebaseSingleton
import com.componentes.asab_app.data.dto.SongDTO
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

class SongCTO {

    /**
     * Esta funcion tiene la finalidad de Guardar una cancion en la coleccion de Song
     */
    fun newSong(song: SongDTO):Boolean{
        //val map = util.objectToMap(song)

        val map = hashMapOf(
            "id" to song.id,
            "name" to song.name,
            "lyrics" to song.lyrics
        )

        return try {
            FirebaseSingleton.getInstance().collection("song").add(map)
            true
        }catch (e:FirebaseFirestoreException){false}
    }
}
