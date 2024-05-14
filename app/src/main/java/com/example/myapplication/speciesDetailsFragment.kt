package com.example.myapplication

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.myapplication.adapter.SpeciesAdapter
import com.example.myapplication.database.AnimalDatabase
import com.example.myapplication.model.Animal
import com.example.myapplication.model.Species
import com.example.myapplication.viewModel.AnimalViewModel
import com.example.myapplication.viewModel.SpeciesViewModel
import com.example.myapplication.viewModel.SpeciesViewModelFactory
import com.example.myapplication.viewModel.ViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class speciesDetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_species_details, container, false)
//        (activity as AppCompatActivity).supportActionBar?.title = "Species Detail"
//        activity.supportA
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Species Details"
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val applicationContext = requireContext().applicationContext
        val db =
            Room.databaseBuilder(
                applicationContext,
                AnimalDatabase::class.java, "santosh-db1"
            ).build()

        val speciesDao = db.speciesDao();
        val speciesViewModelFactory = SpeciesViewModelFactory(speciesDao)
        val viewModel = ViewModelProvider(this, speciesViewModelFactory).get(SpeciesViewModel::class.java)

        val adapter = SpeciesAdapter()

        // RecyclerView setup
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Observe LiveData from ViewModel
        viewModel.getAllSpecies().observe(viewLifecycleOwner, { species ->
            adapter.submitList(species)
        })

        // Floating Action Button setup
        val fab = view.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            context?.let { it1 -> showInputDialog(it1, viewModel) }
        }
        return view;
    }


    fun showInputDialog(context: Context, viewModel: SpeciesViewModel) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle("Add Species")

        // Inflate the layout containing the input fields
        val inflater = LayoutInflater.from(context)
        val dialogLayout = inflater.inflate(R.layout.dialog_input_species, null)

        // Find the EditText fields from the inflated layout
        val inputName = dialogLayout.findViewById<EditText>(R.id.input_name)
        val inputHabitat = dialogLayout.findViewById<EditText>(R.id.input_description)

        alertDialogBuilder.setView(dialogLayout)

        alertDialogBuilder.setPositiveButton("OK") { _, _ ->
            val name = inputName.text.toString()
            val habitat = inputHabitat.text.toString()
            val animal = Species(name = name, description = habitat)
            val nameRegex = Regex("^[a-zA-Z ]+\$")
            val habitatRegex = Regex("^[a-zA-Z ]+\$")

            if (name.isBlank() || habitat.isBlank()) {
                Toast.makeText(context, "Please fill all the details", Toast.LENGTH_LONG).show()
            } else if (!name.matches(nameRegex) ) {
                Toast.makeText(context, "Name and Description should contain only letters", Toast.LENGTH_LONG).show()
            } else {
                viewModel.insertSpecies(animal)
            }
        }

        alertDialogBuilder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

}