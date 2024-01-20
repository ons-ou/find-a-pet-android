package com.gl4.project.data.repositories

import com.gl4.project.data.DatabaseManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class AuthRepository {

    val userRef = DatabaseManager.getUserReference()
    fun registerUser(email: String, username: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {

        userRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.hasChild(email)) {
                    val newUser = mapOf(
                        "username" to username,
                        "email" to email,
                        "password" to password
                    )
                    userRef.child(email).setValue(newUser)
                    onSuccess()
                } else {
                    onError("User with this email already exists!")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                onError(error.message)
            }
        })
    }

    fun loginUser(email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        userRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.hasChild(email)) {
                    val storedPassword: String? = snapshot.child(email).child("password").getValue(String::class.java)
                    if (storedPassword == password) {
                        onSuccess()
                    } else {
                        onError("Wrong password!")
                    }
                } else {
                    onError("User with this email doesn't exist!")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                onError(error.message)
            }
        })
    }
}
