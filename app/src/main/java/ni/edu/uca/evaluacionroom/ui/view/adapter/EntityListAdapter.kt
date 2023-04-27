package ni.edu.uca.evaluacionroom.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.evaluacionroom.data.database.entities.EntityClass
import ni.edu.uca.evaluacionroom.databinding.EntityListItemBinding

class EntityListAdapter(private val onEntityClicked: (EntityClass) -> Unit) : ListAdapter<EntityClass, EntityListAdapter.EntityViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val binding = EntityListItemBinding.inflate(
            LayoutInflater.from( parent.context )
        )
        return EntityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener{
            onEntityClicked(current)
        }
        holder.bind(current)
    }


    class EntityViewHolder(private val binding: EntityListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(entityClass: EntityClass) {
            binding.tvLabel1.text = entityClass.column1.toString()
            binding.tvLabel2.text = entityClass.column2.toString()
            binding.tvLabel3.text = entityClass.column3.toString()
            binding.tvLabel4.text = entityClass.column4.toString()
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<EntityClass>() {
            override fun areItemsTheSame(oldItem: EntityClass, newItem: EntityClass): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: EntityClass, newItem: EntityClass): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

}