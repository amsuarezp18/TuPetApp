package com.example.tupet.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import com.example.tupet.MapsActivity
import com.example.tupet.R
import com.example.tupet.databinding.FragmentLocationBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass.
 * Use the [LocationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LocationFragment : Fragment() {

    var mapOpened: Boolean = false

    private var _binding: FragmentLocationBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_location, container, false)

        var btnMap =  view.findViewById(R.id.navegarBtn) as AppCompatButton

        btnMap.setOnClickListener{
            mapOpened = true
            val intent = Intent(this.activity, MapsActivity::class.java)
            startActivity(intent)
        }

        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        if(mapOpened){
            parentFragmentManager.beginTransaction().apply{
                replace(R.id.flFragment, LocationFragment())
                commit()
            }
            mapOpened=false
        }
    }


}