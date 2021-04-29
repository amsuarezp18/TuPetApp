package com.example.tupet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tupet.models.Vaccine
import com.example.tupet.models.VaccineModel

class VaccineViewModel: ViewModel()  {

    val vaccineModel = VaccineModel()
    fun fetchData(): LiveData<MutableList<Vaccine>> {

        val mutableData = MutableLiveData<MutableList<Vaccine>>()
        vaccineModel.fetchVaccineData().observeForever {
            mutableData.value = it
        }
        return mutableData
    }

}



