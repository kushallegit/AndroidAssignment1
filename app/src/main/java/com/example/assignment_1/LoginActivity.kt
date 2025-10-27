package com.example.assignment_1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val etUser = findViewById<EditText>(R.id.etUser)
        val etPass = findViewById<EditText>(R.id.etPass)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnBack = findViewById<Button>(R.id.btn_back)

        btnLogin.setOnClickListener {
            val uInput = etUser.text.toString().trim()
            val pInput = etPass.text.toString()

            val sp = getSharedPreferences("local_auth", Context.MODE_PRIVATE)
            val savedUser = sp.getString("username", null)
            val savedPass = sp.getString("password", null)

            if (uInput == savedUser && pInput == savedPass) {
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                startActivity(
                    Intent(this, WelcomeActivity::class.java)
                        .putExtra("username", uInput)
                )
            } else {
                Toast.makeText(this, "Username or password is incorrect. Try again!", Toast.LENGTH_SHORT).show()
            }

        }

        btnBack.setOnClickListener {
            finish() // return to main page
        }
    }
}
