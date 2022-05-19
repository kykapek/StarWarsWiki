package ru.kykapek.starwarswiki.ui.FilmList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.kykapek.starwarswiki.databinding.FilmItemBinding
import ru.kykapek.starwarswiki.models.Film


class FilmListAdapter(private val onClickListener: OnClickListener): PagingDataAdapter<Film, FilmListAdapter.ViewHolder>(
    ItemComparator) {

    inner class ViewHolder(private val binding: FilmItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(film: Film?) = with(binding) {
            tvTitle.text = film?.title
            tvDirector.text = film?.director
            tvProducer.text = film?.producer
            tvReleaseDate.text = film?.releaseDate
        }
    }

    class OnClickListener(val clickListener: (film: Film) -> Unit) {
        fun onClick(film: Film) = clickListener(film)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val film = getItem(position)
        holder.bind(film)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(film!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FilmItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    companion object {
        private val ItemComparator = object : DiffUtil.ItemCallback<Film>() {

            override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
                return oldItem == newItem
            }
        }
    }

}