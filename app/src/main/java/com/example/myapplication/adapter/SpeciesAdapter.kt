package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Animal
import com.example.myapplication.model.Species

class SpeciesAdapter : ListAdapter<Species, SpeciesAdapter.SpeciesViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_species, parent, false)
        return SpeciesViewHolder(view)
    }

    override fun onBindViewHolder(holder: SpeciesViewHolder, position: Int) {
        val currentAnimal = getItem(position)
        holder.bind(currentAnimal)
    }

    class SpeciesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.textViewName)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.textViewDescription)

        fun bind(animal: Species) {
            nameTextView.text = animal.name
            descriptionTextView.text = animal.description
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Species>() {
        override fun areItemsTheSame(oldItem: Species, newItem: Species): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Species, newItem: Species): Boolean {
            return oldItem == newItem
        }
    }
}
