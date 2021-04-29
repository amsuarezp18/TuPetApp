package com.example.tupet.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tupet.R
import com.example.tupet.models.Vaccine
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class VaccineAdapter: RecyclerView.Adapter<VaccineAdapter.ViewHolder>() {

        var vaccine: MutableList<Vaccine> = ArrayList()

        var auth: FirebaseAuth = FirebaseAuth.getInstance()
        var database: FirebaseDatabase? = FirebaseDatabase.getInstance()
        var databaseReference: DatabaseReference? = database?.reference!!.child("vacunas")

        lateinit var otherVaccine: Vaccine

        lateinit var view: View
        lateinit var parent: ViewGroup

        fun updateVaccine(vaccine: MutableList<Vaccine>){
            this.vaccine = vaccine
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
        ): ViewHolder {
            this.parent = parent
            val layoutInflater = LayoutInflater.from(parent.context)
            view = layoutInflater.inflate(R.layout.item_list_vacine, parent, false)
            return ViewHolder(view)
        }

        fun addVaccine(vaccine: Vaccine){
            val currentUser = auth.currentUser
            val currentUserDb = databaseReference?.child((currentUser?.uid!!))

            val key = currentUserDb?.push()?.key
            if (key != null) {
                otherVaccine = Vaccine(key, vaccine.name, vaccine.place, vaccine.dateOne, vaccine.dateTwo)
                currentUserDb?.push().setValue(otherVaccine)
                this.vaccine.add(otherVaccine)
                notifyDataSetChanged()
            }
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            var deleted = false
            val currentUser = auth.currentUser
            val currentUserDb = databaseReference?.child((currentUser?.uid!!))
            //databaseReference?.setValue(null)

            var view2 = LayoutInflater.from(parent.context).inflate(
                    R.layout.pop_add_vaccine,
                    parent,
                    false
            )

            if (!deleted) {
                val item = vaccine.get(position)
                holder.bind(item)
            }
        }

        override fun getItemCount(): Int {
            return vaccine.size
        }

        class ViewHolder(view: View): RecyclerView.ViewHolder(view){
            val saveVaccine = view.findViewById(R.id.txtnombreVacuna) as TextView

            fun bind(vaccine: Vaccine){
                saveVaccine.text = vaccine.name

            }


        }


    }
