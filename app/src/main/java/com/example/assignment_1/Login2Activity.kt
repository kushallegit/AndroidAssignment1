package com.example.assignment_1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment_1.databinding.ActivityLogin2Binding
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class Login2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityLogin2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inflate layout with ViewBinding
        binding = ActivityLogin2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase
        FirebaseApp.initializeApp(this)
        val db = FirebaseFirestore.getInstance()

        // 🔹 Login button logic
        binding.btnDoLogin2.setOnClickListener {
            val username = binding.etUser2.text.toString().trim()
            val password = binding.etPass2.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Retrieve from Firestore
            db.collection("users_online").document(username).get()
                .addOnSuccessListener { doc ->
                    if (doc.exists()) {
                        val savedPass = doc.getString("password") ?: ""
                        if (savedPass == password) {
                            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                            startActivity(
                                Intent(this, WelcomeActivity2::class.java)
                                    .putExtra("username", username)
                            )
                        } else {
                            Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "Username not found", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }

        // 🔹 Back button logic
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}
