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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RecommendFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecommendFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}