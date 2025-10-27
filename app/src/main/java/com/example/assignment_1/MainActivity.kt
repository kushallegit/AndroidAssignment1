package com.example.assignment_1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Navigate to the 4 pages (we add stubs below)
        findViewById<Button>(R.id.btn_signup).setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
        findViewById<Button>(R.id.btn_login).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        findViewById<Button>(R.id.btn_signup2).setOnClickListener {
            startActivity(Intent(this, Signup2Activity::class.java))
        }
        findViewById<Button>(R.id.btn_login2).setOnClickListener {
            startActivity(Intent(this, Login2Activity::class.java))
        }
        findViewById<Button>(R.id.btn_updates).setOnClickListener {
            val uri = Uri.parse("https://play.google.com/store/apps/updates")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }
}