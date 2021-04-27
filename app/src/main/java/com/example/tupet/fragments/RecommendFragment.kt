package com.example.tupet.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tupet.R
import com.example.tupet.databinding.FragmentRecommendBinding

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

        btnrecommend.setOnClickListener{

            if( checkTwo.isChecked  ){

            }
            else if(3==3){

            }
            else if(4 == 4){

            }
            else {

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