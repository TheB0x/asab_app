package com.componentes.asab_app.data.cto

import com.componentes.asab_app.data.config.FirebaseSingleton
import com.componentes.asab_app.data.dto.SongDTO
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

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

    @ExperimentalCoroutinesApi
    fun getDataFromFirestore(data: MutableList<String>) {
        val collection = FirebaseSingleton.getInstance().collection("song")
        collection.get().addOnSuccessListener { querySnapshot ->
            for (document in querySnapshot) {
                document.getString("name")?.let { data.add(it) }
            }
        }.addOnFailureListener { exception ->
            // Manejar el fallo
        }
    }

}
