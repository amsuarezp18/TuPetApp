package com.example.tupet.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.example.tupet.CustomDialog
import com.example.tupet.CustomDialogDos
import com.example.tupet.CustomDialogTres
import com.example.tupet.R
import com.example.tupet.databinding.FragmentRecommendBinding
import com.example.tupet.databinding.PopupRecommendBinding


/**
 * A simple [Fragment] subclass.
 * Use the [RecommendFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecommendFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentRecommendBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRecommendBinding.inflate(inflater, container, false)
        val view = binding.root


        val btnrecommend = binding.recomendacionbtn

        val checkOne = binding.checkboxOne
        val checkTwo = binding.checkboxTwo
        val checkTree = binding.checkboxTwoOne
        val checkFour = binding.checkboxTwoTwo
        val checkFive = binding.checkboxTreeOne
        val checkSix = binding.checkboxTreeTwo
        val checkSeven = binding.checkboxTreeTree
        val checkEight = binding.checkboxTreeFour
        val checkNine = binding.checkboxFourOne
        val checkTen = binding.checkboxFourTwo
        val checkEleven = binding.checkboxFourTree
        val checkTwelve = binding.checkboxFiveOne
        val checkThirteen = binding.checkboxFiveTwo
        val checkFourteen = binding.checkboxFiveTree

        var view2 = LayoutInflater.from(container?.context).inflate(R.layout.popup_recommend, container, false);

        var tipoMascota = view2.findViewById(R.id.fieldTypeOf) as TextView
        var tamanoMascota = view2.findViewById(R.id.fieldTamanoOf) as TextView
        var razasMascotas = view2.findViewById(R.id.fieldRazaOff) as TextView


        btnrecommend.setOnClickListener{

            if( checkTwo.isChecked && checkFive.isChecked && checkFour.isChecked && checkTen.isChecked && checkTwelve.isChecked  ){
                getActivity()?.supportFragmentManager?.let { it1 -> CustomDialog().show(it1, "MyCustomFragmentUno") }
            }
            else if( ( checkNine.isChecked || checkEleven.isChecked ) && ( checkEight.isChecked|| checkSeven.isChecked ) &&
                    checkTree.isChecked && checkOne.isChecked && ( checkFourteen.isChecked|| checkThirteen.isChecked)){
                getActivity()?.supportFragmentManager?.let { it1 -> CustomDialogDos().show(it1, "MyCustomFragmentDos") }
            }
            else if( checkOne.isChecked &&  checkFour.isChecked && checkThirteen.isChecked && checkSix.isChecked && ( checkTen.isChecked || checkNine.isChecked ) ){
                getActivity()?.supportFragmentManager?.let { it1 -> CustomDialogTres().show(it1, "MyCustomFragmentTres") }
            }
            else {
                getActivity()?.supportFragmentManager?.let { it1 -> CustomDialogDos().show(it1, "MyCustomFragmentCuatro") }
            }


        }

        return view
    }


}