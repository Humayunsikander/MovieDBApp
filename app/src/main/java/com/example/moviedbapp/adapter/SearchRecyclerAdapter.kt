package com.example.moviedbapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedbapp.BuildConfig
import com.example.moviedbapp.databinding.SearchRecyclerItemBinding
import com.example.moviedbapp.network.models.movie.MoviesData
import javax.inject.Inject

class SearchRecyclerAdapter @Inject constructor() :
    RecyclerView.Adapter<SearchRecyclerAdapter.SearchViewHolder>() {

    private val className = SearchRecyclerAdapter::class.java.simpleName
    private var movieList = arrayListOf<MoviesData>()
    private lateinit var binding: SearchRecyclerItemBinding
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        binding = SearchRecyclerItemBinding.inflate(inflater)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.tvTitle.isSelected = true
        movieList[position].let { moviesData ->
            moviesData.poster_path.let {
                Log.d(className, "poster path is " + BuildConfig.IMAGE_BASE_URL.plus(it))
                Glide.with(holder.itemView).load(BuildConfig.IMAGE_BASE_URL.plus(it))
                    .into(holder.ivPoster)
            }

            moviesData.title.let {
                holder.tvTitle.text = it
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun sendMoviesToAdapter(movies: ArrayList<MoviesData>) {
        movieList = movies
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class SearchViewHolder(@NonNull binding: SearchRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val ivPoster = binding.ivPoster
        val tvTitle = binding.tvTitle
    }
}