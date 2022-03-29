package com.example.jetsportauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val button = findViewById<Button>(R.id.button)
        val emailView = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val passwordView = findViewById<EditText>(R.id.editTextTextPassword)


        button.setOnClickListener{
            val email: String = emailView.text.toString()
            val password: String = passwordView.text.toString()

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                    OnCompleteListener<AuthResult>{
                        task ->

                        if(task.isSuccessful){
                            val firebaseUser: FirebaseUser = task.result!!.user!!

                            Toast.makeText(this@LoginActivity, "You are registered successfully", Toast.LENGTH_SHORT).show()

                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("user_id", firebaseUser.uid)
                            intent.putExtra("email_id", email)
                            startActivity(intent)
                            finish()
                        }
                        else{
                            Toast.makeText(
                                this@LoginActivity, task.exception!!.message.toString(), Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                )
        }
    }
}