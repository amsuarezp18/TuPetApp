package com.example.tupet.fragments

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import android.widget.EditText
import android.widget.PopupWindow
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tupet.R
import com.example.tupet.adapters.VaccineAdapter
import com.example.tupet.models.Vaccine
import com.example.tupet.viewmodel.VaccineViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass.
 * Use the [VacunaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VacunaFragment : Fragment() {

    lateinit var mRecyclerView: RecyclerView
    val mAdapter: VaccineAdapter = VaccineAdapter()
    val viewModel: VaccineViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_vacuna, container, false)
        mRecyclerView = view.findViewById(R.id.rvSafeVacinne) as RecyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(view.context)

        viewModel.fetchData().observe( viewLifecycleOwner , Observer {
            mAdapter.updateVaccine(it)
            mAdapter.notifyDataSetChanged()
        })
        mRecyclerView.adapter = mAdapter


        var view2 = LayoutInflater.from(container?.context).inflate(R.layout.pop_add_vaccine, container, false);
        var btnAdd = view.findViewById(R.id.fbnAddSafeContact) as FloatingActionButton

        btnAdd.setOnClickListener{
            val popupWindow: PopupWindow = PopupWindow(container?.context)
            popupWindow.contentView = view2
            popupWindow.showAtLocation(view,
                Gravity.CENTER_VERTICAL,
                Gravity.CENTER_HORIZONTAL,
                Gravity.TOP)
            popupWindow.isOutsideTouchable = true
            popupWindow.isFocusable = true
            popupWindow.update()

            var nameInput = view2.findViewById(R.id.txtnombreVacuna) as EditText
            var dateOneInput = view2.findViewById(R.id.txtfechaAplicacion) as EditText
            var dateTwoInput = view2.findViewById(R.id.txtlugarDeaplicacion) as EditText
            var placeInput = view2.findViewById(R.id.segundaDosisRemi) as EditText

            val btnClose = view2.findViewById(R.id.cancelBtnVaccine) as Button
            val btnSave = view2.findViewById(R.id.saveBtnVaccine) as Button

            btnSave.setOnClickListener{
                (mRecyclerView.adapter as VaccineAdapter).addVaccine(Vaccine("", nameInput.text.toString(), placeInput.text.toString(), dateOneInput.text.toString(), dateTwoInput.text.toString()))
                nameInput.setText("")
                dateOneInput.setText("")
                dateTwoInput.setText("")
                placeInput.setText("")

                popupWindow.dismiss()
            }
            btnClose.setOnClickListener{
                nameInput.setText("")
                dateOneInput.setText("")
                dateTwoInput.setText("")
                placeInput.setText("")
                popupWindow.dismiss()
            }
        }


        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
    }

}