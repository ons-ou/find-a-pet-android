package com.gl4.project.data

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object DatabaseManager {
    private val databaseReference: DatabaseReference by lazy {
        FirebaseDatabase.getInstance().getReferenceFromUrl("https://find-a-pet-6b71b-default-rtdb.firebaseio.com/")
    }

    fun getUserReference(): DatabaseReference {
        return databaseReference.child("users")
    }
}
