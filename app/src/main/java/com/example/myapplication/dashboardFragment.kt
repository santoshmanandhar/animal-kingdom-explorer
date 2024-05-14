package com.example.myapplication

import AnimalAdapter
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.myapplication.database.AnimalDatabase
import com.example.myapplication.model.Animal
import com.example.myapplication.viewModel.AnimalViewModel
import com.example.myapplication.viewModel.ViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton


class dashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        (activity as? AppCompatActivity)?.supportActionBar?.title = "Home"
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)


        val view =  inflater.inflate(R.layout.fragment_dashboard, container, false)

        val buttonAnimal: Button = view.findViewById(R.id.animal_button);
        val buttonSpecies: Button = view.findViewById(R.id.species_button);
        buttonAnimal.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_animalDetailsFragment)
        }

        buttonSpecies.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_speciesDetailsFragment)
        }
        return view;
    }



}