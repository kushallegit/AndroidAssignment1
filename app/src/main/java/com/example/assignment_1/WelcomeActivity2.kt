package com.example.assignment_1

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment_1.databinding.ActivityWelcome2Binding

class WelcomeActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityWelcome2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inflate layout via ViewBinding
        binding = ActivityWelcome2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 🔹 Retrieve username passed from Login2
        val username = intent.getStringExtra("username") ?: "User"
        binding.tvWelcome2.text = "Welcome, $username!"

        // 🔹 Back button → Main page
        binding.btnBack2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intent)
            finish()
        }
    }
}
