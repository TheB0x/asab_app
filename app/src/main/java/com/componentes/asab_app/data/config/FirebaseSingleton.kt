package com.componentes.asab_app.data.config

import com.google.firebase.firestore.FirebaseFirestore

class FirebaseSingleton {
    companion object{
        private var instance:FirebaseFirestore? = null

        fun getInstance(): FirebaseFirestore{
            return instance ?: synchronized(this){
                instance ?: buildFirestoreInstance().also { instance = it }
            }
        }

        private fun buildFirestoreInstance(): FirebaseFirestore {
            return FirebaseFirestore.getInstance()
        }
    }
}