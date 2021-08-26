package com.example.moviedbapp.adapter.movies

import Genres
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedbapp.databinding.GenresItemBinding

//Provides dependency to fragment using @Inject annotation
class MovieGenresAdapter : RecyclerView.Adapter<MovieGenresAdapter.ViewHolder>() {
    private val className = MovieGenresAdapter::class.java.simpleName
    private var oldGenresList = listOf<Genres>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GenresItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        oldGenresList[position].let { genresData ->
            genresData.name.let {
                Log.d(className, "genres name is $it")
                holder.tvGenres.text = it
            }
        }
    }

    class ViewHolder(@NonNull binding: GenresItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvGenres = binding.tvGenres
    }

    override fun getItemCount(): Int {
        return oldGenresList.size
    }

    fun setData(newList: List<Genres>) {
        oldGenresList = newList
        notifyDataSetChanged()
    }

}