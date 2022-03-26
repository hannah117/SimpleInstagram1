package com.codepath.simpleinstagram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        if (ParseUser.getCurrentUser() != null) {
            goToMainActivity()
        }



        findViewById<Button>(R.id.loginBtn).setOnClickListener{
            val username = findViewById<EditText>(R.id.usernameTv).text.toString()
            val password = findViewById<EditText>(R.id.passwordTv).text.toString()
            loginUser(username, password)
        }
        findViewById<Button>(R.id.signUpBtn).setOnClickListener{
            val username = findViewById<EditText>(R.id.usernameTv).text.toString()
            val password = findViewById<EditText>(R.id.passwordTv).text.toString()
            signUpUser(username, password)
        }
    }

    private fun signUpUser(username: String, password: String) {
        // Create the ParseUser
        val user = ParseUser()
        user.username = username
        user.setPassword(password)

        user.signUpInBackground { e ->
            if (e == null) {
                goToMainActivity()
            } else {
                e.printStackTrace()
            }
        }
    }
    private fun loginUser(username: String, password: String) {
        ParseUser.logInInBackground(username, password, ({ user, e ->
            if(user != null) {
                Log.i(TAG, "successful login")
            } else {
                e.printStackTrace();
                Toast.makeText(this, "Error logging in", Toast.LENGTH_SHORT).show()
            }
        }))
    }

    private fun goToMainActivity() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

companion object {
    const val TAG = "LoginActivity"
}
}