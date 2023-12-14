package com.example.swc3404

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var btnLogin: Button
    private lateinit var mEmail: EditText
    private lateinit var mPass: EditText
    private lateinit var mSignUp: TextView
    private lateinit var mDialog: ProgressBar
    private lateinit var processingMessage: TextView

    // Firebase
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        Toast.makeText(this, "Login page", Toast.LENGTH_LONG).show()

        mDialog = findViewById(R.id.progressBar)
        processingMessage = findViewById(R.id.processingMessage)

        loginDetails()
    }

    private fun loginDetails() {
        mEmail = findViewById(R.id.email_login)
        mPass = findViewById(R.id.password_login)
        btnLogin = findViewById(R.id.btn_login)
        mSignUp = findViewById(R.id.signup_reg)

        btnLogin.setOnClickListener {
            val email = mEmail.text.toString().trim()
            val pass = mPass.text.toString().trim()

            if (TextUtils.isEmpty(email)) {
                mEmail.error = "Email is required"
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(pass)) {
                mPass.error = "Please enter the password"
                return@setOnClickListener
            }

            // To show the processing message and ProgressBar
            mDialog.visibility = View.VISIBLE
            processingMessage.visibility = View.VISIBLE

            mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener { task ->
                    mDialog.visibility = View.GONE
                    processingMessage.visibility = View.GONE
                    if (task.isSuccessful) {
                        Toast.makeText(applicationContext, "Login successful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, HomeActivity::class.java)
                        finish()
                        startActivity(intent)
                    } else {
                        Toast.makeText(applicationContext, "Login unsuccessful", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        mSignUp.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            finish()
            startActivity(intent)
        }
    }
}
