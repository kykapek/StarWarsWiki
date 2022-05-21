package ru.kykapek.starwarswiki.ui.FilmDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.kykapek.starwarswiki.databinding.HeroItemBinding
import ru.kykapek.starwarswiki.models.Hero

class FilmDetailsAdapter : ListAdapter<Hero, FilmDetailsAdapter.ViewHolder>(ItemComparator) {

    inner class ViewHolder(private val binding: HeroItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(hero: Hero?) {
            binding.tvBirthday.text = hero?.birthday
            binding.tvGender.text = hero?.gender
            binding.tvHeroName.text = hero?.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(HeroItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hero = getItem(position)
        holder.bind(hero)
    }

    companion object {
        private val ItemComparator = object : DiffUtil.ItemCallback<Hero>() {
            override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean {
                return oldItem == newItem
            }
        }
    }
}