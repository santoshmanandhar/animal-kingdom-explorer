package com.example.myapplication.database;

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.dao.AnimalDao
import com.example.myapplication.dao.SpeciesDao
import com.example.myapplication.model.Animal
import com.example.myapplication.model.Species

@Database(entities = [Animal::class, Species::class], version = 4)
abstract class AnimalDatabase : RoomDatabase() {

    abstract fun animalDao(): AnimalDao

    abstract  fun speciesDao(): SpeciesDao

//    companion object {
//        private var INSTANCE: AnimalDatabase? = null
//
//        fun getInstance(context: Context): AnimalDatabase {
//            synchronized(this) {
//                if (INSTANCE == null) {
//                    INSTANCE = Room.databaseBuilder(
//                        context.applicationContext,
//                        AnimalDatabase::class.java,
//                        "animal_database"
//                    ).build()
//                }
//            }
//            return INSTANCE!!
//        }
//    }


}
