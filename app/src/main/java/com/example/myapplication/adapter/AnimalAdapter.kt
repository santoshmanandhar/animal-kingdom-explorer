import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.Animal
import com.example.myapplication.R

class AnimalAdapter : ListAdapter<Animal, AnimalAdapter.AnimalViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_animal, parent, false)
        return AnimalViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val currentAnimal = getItem(position)
        holder.bind(currentAnimal)
    }

    class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.textViewName)
        private val habitatTextView: TextView = itemView.findViewById(R.id.textViewHabitat)
        private val dietTextView: TextView = itemView.findViewById(R.id.textViewDiet)

        fun bind(animal: Animal) {
            nameTextView.text = animal.name
            habitatTextView.text = animal.habitat
            dietTextView.text = animal.diet
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Animal>() {
        override fun areItemsTheSame(oldItem: Animal, newItem: Animal): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Animal, newItem: Animal): Boolean {
            return oldItem == newItem
        }
    }
}
