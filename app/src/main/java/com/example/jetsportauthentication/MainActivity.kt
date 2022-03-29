package com.example.jetsportauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userID = intent.getStringExtra("user_id")
        val emailID = intent.getStringExtra("email_id")

        val emailView = findViewById<TextView>(R.id.textView5)
        val userIDView = findViewById<TextView>(R.id.textView6)
        val logoutButtonView = findViewById<Button>(R.id.button2)

        emailView.text = emailID
        userIDView.text = userID

        logoutButtonView.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            startActivity(Intent(this@MainActivity, LoginLoginActivity::class.java))
            finish()
        }

    }
}