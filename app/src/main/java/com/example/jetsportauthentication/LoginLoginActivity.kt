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

class LoginLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_login)

        val emailView = findViewById<EditText>(R.id.editTextTextEmailAddress4)
        val passwordView = findViewById<EditText>(R.id.editTextTextPassword2)
        val buttonLoginView = findViewById<Button>(R.id.button3)

        buttonLoginView.setOnClickListener{
            val email: String = emailView.text.toString()
            val password: String = passwordView.text.toString()

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                    OnCompleteListener<AuthResult>{
                            task ->

                        if(task.isSuccessful){
                            val firebaseUser: FirebaseUser = task.result!!.user!!

                            Toast.makeText(this@LoginLoginActivity, "You are registered successfully", Toast.LENGTH_SHORT).show()

                            val intent = Intent(this@LoginLoginActivity, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("user_id", firebaseUser.uid)
                            intent.putExtra("email_id", email)
                            startActivity(intent)
                            finish()
                        }
                        else{
                            Toast.makeText(
                                this@LoginLoginActivity, task.exception!!.message.toString(), Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                )
        }
    }




}