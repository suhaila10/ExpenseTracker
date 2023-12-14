package com.example.swc3404

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class UserManual : AppCompatActivity() {

    //decalre all the button
    lateinit var btnA: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_manual)

        //initialize all button
        btnA = findViewById<Button>(R.id.btnBTD)

        //function press button for button back to dashboard
        btnA.setOnClickListener {

            //declare variable i to connect tonext pages/activity = DasboardFragment
            val i = Intent (this, DashboardFragment::class.java)
            startActivity(i);
        }

    }
}