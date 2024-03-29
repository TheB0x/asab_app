package com.componentes.asab_app.data.cto

import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.componentes.asab_app.data.config.FirebaseSingleton
import com.componentes.asab_app.data.dto.SongDTO
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*
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

    @ExperimentalCoroutinesApi
    fun getDetailData(data: MutableList<String>, name:String){
        val collection = FirebaseSingleton.getInstance().collection("song")
        collection
            .whereEqualTo("name", name)
            .get().addOnSuccessListener {
                querySnapshot -> for (document in querySnapshot){
                document.getString("lyrics")?.let { data.add(it) }
            }
        }.addOnFailureListener { exception -> "error"}
    }

    fun deleteSong(name: String): Boolean{

        return try {
            FirebaseSingleton
                .getInstance()
                .collection("song")
                .whereEqualTo("name", name)
                .get()
                .addOnSuccessListener { querySnapshots ->
                for (document in querySnapshots){
                    document.reference.delete()
                }
            }
            true
        }catch (e:FirebaseFirestoreException){false}

    }


}
