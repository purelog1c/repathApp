package com.company.repathapp.view

import android.content.ContentValues.TAG
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.company.repathapp.R
import com.company.repathapp.databinding.ActivityRegisterBinding
import com.company.repathapp.utils.FirebaseUtils
import com.company.repathapp.utils.PasswordStrengthCalculator
import com.company.repathapp.utils.StrengthLevel
import com.company.repathapp.viewmodel.UserRegistrationViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import java.util.*


class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        var color: Int = R.color.weak
        val passwordStrengthCalculator = PasswordStrengthCalculator()

        super.onCreate(savedInstanceState)

        val userRegistrationViewModel = ViewModelProvider(this).get(UserRegistrationViewModel::class.java)
        val registerBinding = DataBindingUtil.setContentView<ActivityRegisterBinding>(this,R.layout.activity_register)

        registerBinding.lifecycleOwner = this
        registerBinding.userRegistrationViewModel = userRegistrationViewModel
        registerBinding.passwordInput.addTextChangedListener(passwordStrengthCalculator)
        val strength = registerBinding.passwordInput.addTextChangedListener(passwordStrengthCalculator)

        userRegistrationViewModel.getRegisteringUser()!!.observe(this, {User ->

            if (TextUtils.isEmpty(Objects.requireNonNull(User).getEmail())) {
                registerBinding.registerEmail.error = "Enter an E-Mail Address"
                registerBinding.registerEmail.requestFocus()
            }else if (!User.isValidEmail()) {
                registerBinding.registerEmail.error = ("Enter a Valid E-mail Address")
                registerBinding.registerEmail.requestFocus()
            }else if(TextUtils.isEmpty(Objects.requireNonNull(User).getPassword())){
                registerBinding.passwordInput.error = ("Enter a Password")
                registerBinding.passwordInput.requestFocus()
            }else if (!User.isPasswordLengthGreaterThan5()) {
                registerBinding.passwordInput.error = ("Password length should be more than 6 digits")
                registerBinding.passwordInput.requestFocus()
            }else if(!User.isPasswordVerified(registerBinding.editTextPassword.text.toString())){
                registerBinding.editTextPassword.error = "password is not verified!"
                registerBinding.editTextPassword.requestFocus()
            }else {


                FirebaseAuth.getInstance().createUserWithEmailAndPassword(User.getEmail().toString(), User.getPassword().toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {

                            var firebaseUser: FirebaseUser = task.result!!.user!!
                            Toast.makeText(
                                this@RegistrationActivity,
                                "You are successfully Registered",
                                Toast.LENGTH_SHORT
                            ).show()

                            val userData = hashMapOf<String, Any>(
                                "name" to User.getName().toString(),
                                "surname" to User.getSurname().toString(),
                                "email" to User.getEmail().toString(),
                                "UID" to firebaseUser.uid
                            )
                            FirebaseUtils().fireStoreDatabase.collection("users").add(userData)
                                    .addOnSuccessListener {
                                        Log.d(TAG, "Added document with ID ${it.id}")
                                    }
                                    .addOnFailureListener { exception ->
                                        Log.w(TAG, "Error adding document $exception")
                                    }
                        }
                        else{
                            Toast.makeText(
                                this@RegistrationActivity,
                                "User already exists!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                Log.i("Success", "Success")}
        })


        fun displayStrengthLevel(strengthLevel: StrengthLevel) {

            //registerBinding.strengthLevelTxt.text = strengthLevel.name
            registerBinding.strengthLevelTxt.setTextColor(Color.WHITE)
            registerBinding.strengthLevelTxt.text = strengthLevel.name
            Log.i("strength level", strengthLevel.name)
            //registerBinding.strengthLevelTxt.setTextColor(ContextCompat.getColor(this, color))
            registerBinding.strengthLevelTxt.setBackgroundColor(ContextCompat.getColor(this, color))
        }
        passwordStrengthCalculator.strengthColor.observe(this, {strengthColor ->
            color = strengthColor
        })
        passwordStrengthCalculator.strengthLevel.observe(this, {strengthLevel ->
            displayStrengthLevel(strengthLevel)
        })

    }
}