package ru.kykapek.starwarswiki.ui.FilmList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kykapek.starwarswiki.databinding.FilmItemBinding
import ru.kykapek.starwarswiki.models.Film

class FilmListNewAdapter(private val listener: FilmItemListener) : RecyclerView.Adapter<ViewHolder>() {

    interface FilmItemListener {
        fun onClickedFilm(film: Film)
    }

    private val items = ArrayList<Film>()

    fun setItems(items: ArrayList<Film>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: FilmItemBinding = FilmItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])
}

class ViewHolder(private val itemBinding: FilmItemBinding, private val listener: FilmListNewAdapter.FilmItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var film: Film

    init {
        itemBinding.root.setOnClickListener(this)
    }

    //@SuppressLint("SetTextI18n")
    fun bind(item: Film) {
        this.film = item
        itemBinding.tvTitle.text = item.title
        itemBinding.tvDirector.text = item.director
        itemBinding.tvProducer.text = item.producer
        itemBinding.tvReleaseDate.text = item.releaseDate
    }

    override fun onClick(v: View?) {
        listener.onClickedFilm(film)
    }
}