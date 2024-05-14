package com.example.myapplication.dao

import android.widget.GridLayout.Spec
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.model.Animal
import com.example.myapplication.model.Species

@Dao
interface SpeciesDao {

    @Query("select * from species")
    fun getAllSpecies(): LiveData<List<Species>>

    @Query("select * from species where id = :speciesId")
    fun getSpeciesById(speciesId: Long): LiveData<Species>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(species: Species)
}