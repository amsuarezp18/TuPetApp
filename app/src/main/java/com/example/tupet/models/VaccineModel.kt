package com.example.tupet.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class VaccineModel {

    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance()
    private val databaseReference = database?.reference!!.child("vacunas")

    var vaccine: MutableList<Vaccine> = ArrayList()

    fun fetchVaccineData(): LiveData<MutableList<Vaccine>> {
        val mutableLiveData = MutableLiveData<MutableList<Vaccine>>()
        val currentUser = auth.currentUser
        val currentUserDb = databaseReference?.child((currentUser?.uid!!))
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                if(vaccine.size != 0){
                    vaccine = ArrayList()
                }
                for( ds in dataSnapshot.children){
                    val name: String = ds.child("name").getValue(String::class.java)!!
                    val place: String = ds.child("place").getValue(String::class.java)!!
                    val dateOne: String = ds.child("dateOne").getValue(String::class.java)!!
                    val dateTwo: String = ds.child("dateTwo").getValue(String::class.java)!!
                    val key: String = ds.key.toString()
                    vaccine.add(Vaccine(key,name, place,dateOne,dateTwo ))
                }
                mutableLiveData.value = vaccine
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("firebase", "loadPost:onCancelled", databaseError.toException())
            }
        }

        currentUserDb?.addValueEventListener(postListener)
        mutableLiveData.value = vaccine
        return mutableLiveData

    }

}