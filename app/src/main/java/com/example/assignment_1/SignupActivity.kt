package com.example.assignment_1

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)


        // Find views
        val etUser = findViewById<EditText>(R.id.etUser)
        val etPass = findViewById<EditText>(R.id.etPass)
        val etConfirm = findViewById<EditText>(R.id.etConfirm)
        val btnSignup = findViewById<Button>(R.id.btnDoSignup)
        val btnBack = findViewById<Button>(R.id.btn_back)

        // Save credentials locally (SharedPreferences)
        btnSignup.setOnClickListener {
            val username = etUser.text.toString().trim()
            val password = etPass.text.toString()
            val confirm = etConfirm.text.toString()

            if (username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirm) {
                Toast.makeText(this, "Passwords don’t match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val sharedPref = getSharedPreferences("local_auth", Context.MODE_PRIVATE)
            sharedPref.edit()
                .putString("username", username)
                .putString("password", password)
                .apply()

            Toast.makeText(this, "Signup successful! Saved locally.", Toast.LENGTH_SHORT).show()
            finish() // Go back to Main Page
        }

        // Back button to main page
        btnBack.setOnClickListener {
            finish()
        }
    }
}
