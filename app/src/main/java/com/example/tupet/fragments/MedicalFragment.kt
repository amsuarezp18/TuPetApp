package com.example.tupet.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import com.example.tupet.R
import com.example.tupet.databinding.FragmentAccountBinding
import com.example.tupet.databinding.FragmentMedicalBinding


/**
 * A simple [Fragment] subclass.
 * Use the [MedicalFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MedicalFragment : Fragment() {

    private var _binding: FragmentMedicalBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMedicalBinding.inflate(inflater, container, false)
        val view = binding.root

        val btnVacuna = binding.cardOne as AppCompatButton

        btnVacuna.setOnClickListener{
            changeView()
        }

        return view
    }

    private fun changeView(){
        parentFragmentManager.beginTransaction().apply{
            replace(R.id.flFragment, VacunaFragment())
            commit()
        }
    }


}