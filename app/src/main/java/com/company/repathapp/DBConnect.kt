package com.company.repathapp

import android.content.ContentValues.TAG
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class DBConnect : AppCompatActivity() {

    var db = FirebaseFirestore.getInstance()

    public fun setDocument(){
        val user = hashMapOf(
            "address" to "Kaunas",
            "name" to "asdasdsadd",
            "password" to "123456",
            "surename" to "Canbulut",
            "username" to "Mitraya"
        )
        db.collection("users").document("\n" +
                "F4o4C99eYpLjmMQ4b8Uf").set(user)
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
    }
}