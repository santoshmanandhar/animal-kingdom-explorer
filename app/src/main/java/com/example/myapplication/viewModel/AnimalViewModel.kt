package com.example.myapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.AnimalRepository
import com.example.myapplication.dao.AnimalDao
import com.example.myapplication.model.Animal
import kotlinx.coroutines.launch

class AnimalViewModel(private val animalDao: AnimalDao) : ViewModel() {

//
    fun getAllAnimals(): LiveData<List<Animal>> {
        return animalDao.getAllAnimals()
    }

    fun insertAnimal(animal: Animal) {
        viewModelScope.launch {
            animalDao.insert(animal)
        }
    }
}