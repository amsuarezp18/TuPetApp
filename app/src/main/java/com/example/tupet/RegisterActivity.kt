package com.example.tupet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.tupet.databinding.ActivityLoginBinding
import com.example.tupet.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding

    lateinit var auth: FirebaseAuth
    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null
    lateinit var tokenToSent: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.arrowImg.setOnClickListener{
            val intent = Intent(this, LauncherActivity::class.java)
            startActivity(intent)
        }

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")

        register()
    }

    private fun register(){

        binding.signupbtn.setOnClickListener{

                var passwordValidator = Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,}\$")
                var emailValidator = Regex("^([\\w\\.\\-]+)@([\\w\\-]+)((\\.(\\w){2,3})+)\$")

                val firstName = binding.firstNameRegister
                val lastName = binding.lastNameRegister
                val email = binding.emailRegister
                val password = binding.passwordRegister
                val petName = binding.namePetRegister

                if(TextUtils.isEmpty(firstName.text.toString())){
                    firstName.setError("Por favor ingresar un nombre  ")
                    return@setOnClickListener
                } else if(TextUtils.isEmpty(lastName.text.toString())){
                    lastName.setError("Por favor ingresar un apellido")
                    return@setOnClickListener
                } else if(TextUtils.isEmpty(petName.text.toString())){
                    lastName.setError("Por favor ingresar el nombre de una mascota")
                    return@setOnClickListener
                } else if(TextUtils.isEmpty(email.text.toString())){
                    lastName.setError("Por favor ingresar un correo electrónico")
                    return@setOnClickListener
                } else if(TextUtils.isEmpty(password.text.toString())){
                    lastName.setError("Por favor ingresar una contraseña")
                    return@setOnClickListener
                }
                else if(!emailValidator.matches(email.text.toString())){
                    email.setError("Por favor ingresar un correo valido")
                    return@setOnClickListener
                } else if(!passwordValidator.matches(password.text.toString())){
                    password.setError("Por favor ingresar una contraseña valida alfa-numerica")
                    return@setOnClickListener
                }
                else {
                    auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString()).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val currentUser = auth.currentUser
                            val currentUserDb = databaseReference?.child((currentUser?.uid!!))

                            currentUserDb?.child("firstname")?.setValue(firstName.text.toString())
                            currentUserDb?.child("lastname")?.setValue(lastName.text.toString())
                            currentUserDb?.child("petname")?.setValue(petName.text.toString())

                            Toast.makeText(this, "Registration Succes", Toast.LENGTH_LONG).show()
                            val intent = Intent(this, LauncherActivity::class.java)
                            startActivity(intent)
                            finish()


                        } else {
                            Toast.makeText(this, "El registro fallo, por favor intentar de nuevo", Toast.LENGTH_LONG).show()
                        }
                    }

                }
            }
        }
    }
