package com.example.tupet

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.tupet.databinding.ActivityMainBinding
import com.example.tupet.fragments.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var mapOpened: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firstFragment = AccountFragment()
        val secondFragment = FindFragment()
        val thirdFragment =  LocationFragment()
        val fourthFragment = MedicalFragment()
        val fifthFragment = RecommendFragment()

        setCurrentFragment(firstFragment)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.findPet -> {setCurrentFragment(secondFragment); this.mapOpened = false}
                R.id.locationMap -> {setCurrentFragment(thirdFragment); this.mapOpened = true}
                R.id.medical -> {setCurrentFragment(fourthFragment); this.mapOpened = false}
                R.id.account -> {setCurrentFragment(firstFragment); this.mapOpened = false}
                R.id.recommendPet -> {setCurrentFragment(fifthFragment); this.mapOpened = false}
            }
            true
        }

    }

    override fun onResume() {
        super.onResume()
        if(this.mapOpened){
            setCurrentFragment(LocationFragment())
            this.mapOpened = false
        }
    }

    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
    }
}