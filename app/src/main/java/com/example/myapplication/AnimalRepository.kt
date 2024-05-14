package com.example.myapplication

import androidx.lifecycle.LiveData
import com.example.myapplication.dao.AnimalDao
import com.example.myapplication.model.Animal

class AnimalRepository(private val animalDao: AnimalDao) {

    fun getAllAnimals(): LiveData<List<Animal>> {
        return animalDao.getAllAnimals()
    }

    suspend fun insertAnimal(animal: Animal) {
        animalDao.insert(animal)
    }

    // Add other CRUD methods as needed
}