package com.gl4.project.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gl4.project.databinding.ActivityRegisterBinding


import android.content.Intent
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class Register : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    var dbref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://find-a-pet-6b71b-default-rtdb.firebaseio.com/")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerbutton.setOnClickListener{

            if(binding.email.text.toString().isEmpty() ||binding.username.text.toString().isEmpty() || binding.password.text.toString().isEmpty() || binding.password2.text.toString().isEmpty()){
                Toast.makeText(applicationContext, "Please fill all the form!", Toast.LENGTH_SHORT).show()
            }else if(!binding.password.text.toString().equals(binding.password2.text.toString())){
                Toast.makeText(applicationContext, "Please verify your pasword!", Toast.LENGTH_SHORT).show()
            }else{
                dbref.child("users").addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.hasChild(binding.email.text.toString())) {
                            Toast.makeText(applicationContext, "User with this email already exists!", Toast.LENGTH_SHORT).show()
                        } else {
                            val newUser = mapOf(
                                "username" to binding.username.text.toString(),
                                "email" to binding.email.text.toString(),
                                "password" to binding.password.text.toString()
                            )
                            dbref.child("users").child(binding.email.text.toString()).setValue(newUser)
                            val intent = Intent(this@Register, MainActivity::class.java)
                            startActivity(intent)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        // Handle errors
                    }
                })
            }
        }
        binding.login.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}