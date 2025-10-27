package com.example.assignment_1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class Login2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login2)

        // Initialize Firebase
        FirebaseApp.initializeApp(this)
        val db = FirebaseFirestore.getInstance()

        val etUser = findViewById<EditText>(R.id.etUser2)
        val etPass = findViewById<EditText>(R.id.etPass2)
        val btnLogin = findViewById<Button>(R.id.btnDoLogin2)
        val btnBack = findViewById<Button>(R.id.btnBack)

        btnLogin.setOnClickListener {
            val username = etUser.text.toString().trim()
            val password = etPass.text.toString()

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
                            startActivity(Intent(this, WelcomeActivity2::class.java)
                                .putExtra("username", username))
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

        btnBack.setOnClickListener { finish() }
    }
}
