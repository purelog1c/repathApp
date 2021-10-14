package com.company.repathapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //UI Initialization
        val loginBtn: Button = findViewById(R.id.loginButton)
        val signUpBtn: Button = findViewById(R.id.signUpButton)
        val emailText: EditText = findViewById(R.id.emailText)

        //Login Button Initialization
        loginBtn.isClickable = false;
        loginBtn.setTextColor(Color.parseColor("#B2B2B2"))
        loginBtn.setBackgroundColor(Color.parseColor("#686868"))

        // Attributes of Email Field
        emailText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if(!isValidEmailInput(emailText)){
                    loginBtn.isClickable = false;
                    loginBtn.setTextColor(Color.parseColor("#B2B2B2"))
                    loginBtn.setBackgroundColor(Color.parseColor("#686868"))
                }else{
                    loginBtn.setTextColor(Color.parseColor("#FFFFFF"))
                    loginBtn.setBackgroundColor(Color.parseColor("#6200EE"))
                    loginBtn.isClickable = true;
                }
            }
        })




            //GIVE ERROR MESSAGE WHEN FOCUS IS OFF

/*        emailText.setOnFocusChangeListener { view, b ->
            if(!b) {
                emailText.setTextColor(Color.parseColor("red"))
                Log.i("IS it Valid ", "" + isValidEmail(emailText.text.toString()))
            }
        }*/

        signUpBtn.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


        //TESTING DATA WRITE

        val btnDataWrite: Button = findViewById<Button>(R.id.createData)

        btnDataWrite.setOnClickListener {
        }
        // TESTING FINISHED


    }
}