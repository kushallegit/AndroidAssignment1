package com.example.assignment_1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome2)

        val tvWelcome = findViewById<TextView>(R.id.tvWelcome2)
        val btnBack = findViewById<Button>(R.id.btnBack2)

        // Retrieve username passed from Login2
        val username = intent.getStringExtra("username") ?: "User"
        tvWelcome.text = "Welcome, $username!"

        // Back button → Main page
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intent)
            finish()
        }
    }
}
