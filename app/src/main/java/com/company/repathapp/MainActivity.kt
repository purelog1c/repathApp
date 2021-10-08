package com.company.repathapp

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

        val writeDataDB = DBConnect()
        val loginBtn: Button = findViewById(R.id.loginButton)

        val emailText: EditText = findViewById(R.id.emailText)


        fun isValidEmail(email: String): Boolean {
            return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun String.isValidEmail() =
            !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()



        emailText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if(!isValidEmail(emailText.text.toString())){
                    emailText.setTextColor(Color.parseColor("red"))
                    loginBtn.isClickable = false;
                    loginBtn.setTextColor(Color.parseColor("#B2B2B2"))
                    loginBtn.setBackgroundColor(Color.parseColor("#686868"))

                }
                else{
                    emailText.setTextColor(Color.parseColor("#757575"))
                    loginBtn.setTextColor(Color.parseColor("#FFFFFF"))
                    loginBtn.setBackgroundColor(Color.parseColor("#6200EE"))
                    loginBtn.isClickable = true;
                }
            }
        })

        loginBtn.isClickable = false;
        loginBtn.setTextColor(Color.parseColor("#B2B2B2"))
        loginBtn.setBackgroundColor(Color.parseColor("#686868"))


            //GIVE ERROR MESSAGE WHEN FOCUS IS OFF

/*        emailText.setOnFocusChangeListener { view, b ->
            if(!b) {
                emailText.setTextColor(Color.parseColor("red"))
                Log.i("IS it Valid ", "" + isValidEmail(emailText.text.toString()))
            }
        }*/




        loginBtn.setOnClickListener {
        }


        //TESTING DATA WRITE

        val btnDataWrite: Button = findViewById<Button>(R.id.createData)

        btnDataWrite.setOnClickListener {
            writeDataDB.setDocument()
        }
        // TESTING FINISHED


    }
}