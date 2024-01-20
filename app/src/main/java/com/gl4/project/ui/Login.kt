package com.gl4.project.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat.ThemeCompat
import com.gl4.project.R
import com.gl4.project.databinding.ActivityLoginBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    var dbref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://find-a-pet-6b71b-default-rtdb.firebaseio.com/")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setTheme(ThemeCompat)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginbutton.setOnClickListener {
            if (binding.email.text.toString().isEmpty() || binding.password.text.toString().isEmpty()) {
                Toast.makeText(applicationContext, "Please enter your username & password!", Toast.LENGTH_SHORT).show()
            } else {
                dbref.child("users").addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.hasChild(binding.email.text.toString())) {
                            val password: String? = snapshot.child(binding.email.text.toString()).child("password").getValue(String::class.java)
                            if (!password.isNullOrBlank() && password == binding.password.text.toString()) {
                                val intent = Intent(this@Login, MainActivity::class.java)
                                startActivity(intent)
                                finish() // Optional: Finish this activity to prevent going back to login with the back button

                            } else {
                                Toast.makeText(applicationContext, "Wrong password!", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(applicationContext, "User with this email doesn't exist!", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        // Handle errors
                        Toast.makeText(applicationContext, "Database error: ${error.message}", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }

        binding.register.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }
}
