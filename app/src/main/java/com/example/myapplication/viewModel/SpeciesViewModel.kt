package com.example.myapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.dao.AnimalDao
import com.example.myapplication.dao.SpeciesDao
import com.example.myapplication.model.Animal
import com.example.myapplication.model.Species
import kotlinx.coroutines.launch

class SpeciesViewModel(private val speciesDao: SpeciesDao) : ViewModel() {

    //
    fun getAllSpecies(): LiveData<List<Species>> {
        return speciesDao.getAllSpecies()
    }

    fun insertSpecies(species: Species) {
        viewModelScope.launch {
            speciesDao.insert(species)
        }
    }
}