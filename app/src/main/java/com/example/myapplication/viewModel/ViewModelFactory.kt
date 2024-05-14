package com.example.myapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.dao.AnimalDao
import com.example.myapplication.dao.SpeciesDao

class ViewModelFactory(private val animalDao: AnimalDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnimalViewModel::class.java)) {
            return AnimalViewModel(animalDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class SpeciesViewModelFactory(private val speciesDao: SpeciesDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SpeciesViewModel::class.java)) {
            return SpeciesViewModel(speciesDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}