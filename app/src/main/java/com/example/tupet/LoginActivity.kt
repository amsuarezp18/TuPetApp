package com.example.tupet

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.tupet.databinding.ActivityLauncherBinding
import com.example.tupet.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    lateinit var auth: FirebaseAuth
    lateinit var sharedPreferences: SharedPreferences

    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.arrowImg.setOnClickListener{
            val intent = Intent(this, LauncherActivity::class.java)
            startActivity(intent)
        }

        auth = FirebaseAuth.getInstance()

        sharedPreferences = getSharedPreferences( "SHARED_PREF", Context.MODE_PRIVATE)

        loginEmailPassword()


        binding.fabGoogle.setOnClickListener{
            Toast.makeText(this, "We are working to provide you soon this option", Toast.LENGTH_LONG).show()
        }


        binding.fabFacebook.setOnClickListener{
            Toast.makeText(this, "We are working to provide you soon this option", Toast.LENGTH_LONG).show()
        }

        binding.fabTwitter.setOnClickListener{
            Toast.makeText(this, "We are working to provide you soon this option", Toast.LENGTH_LONG).show()
        }

    }


    private fun loginEmailPassword() {

        val email = binding.emailLogin
        val password = binding.passwordLogin

        binding.loginbtn.setOnClickListener{

                if(TextUtils.isEmpty(email.text.toString())){
                    email.setError("Por favor ingresar un correo ")
                    return@setOnClickListener
                } else if(TextUtils.isEmpty(password.text.toString())  ){
                    password.setError("Por favor ingresar una contraseña valida")
                    return@setOnClickListener
                }

                auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString()).addOnCompleteListener{
                    if(it.isSuccessful){
                        loadProfile()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()

                    }else {
                        Toast.makeText(this, "Login fallo, por favor intentar de nuevo", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

    private fun loadProfile(){
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")
        val currentUser = auth.currentUser
        val userreference = databaseReference?.child((currentUser?.uid!!))

        userreference?.get()?.addOnSuccessListener {
            val name = it.child("firstname").getValue()
            val lastname = it.child("lastname").getValue()
            val petname = it.child("petname").getValue()

            Log.e("TUJA", name.toString() + lastname.toString())
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("FIRSTNAME", name.toString())
            editor.putString("LASTNAME", lastname.toString())
            editor.putString("PETNAME", petname.toString())
            editor.apply()

        }?.addOnFailureListener{
            Log.e("TUJA", "Error getting data", it)
        }

    }

}