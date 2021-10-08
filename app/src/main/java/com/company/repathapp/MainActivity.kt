package com.company.repathapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.firestore.FirebaseFirestore




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val writeDataDB = DBConnect()

        val btnDataWrite: Button = findViewById<Button>(R.id.createData)

        btnDataWrite.setOnClickListener{
            writeDataDB.setDocument()
        }
    }

}