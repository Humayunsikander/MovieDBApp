package com.example.moviedbapp.adapter.movies

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedbapp.BuildConfig
import com.example.moviedbapp.databinding.RecyclerviewItemBinding
import com.example.moviedbapp.network.models.movie.MoviesData
import com.example.moviedbapp.utils.DiffUtils
import javax.inject.Inject

//Provides dependency to fragment using @Inject annotation
class PopularMoviesAdapter @Inject constructor() :
    RecyclerView.Adapter<PopularMoviesAdapter.RecyclerViewHolder>() {
    private val className = PopularMoviesAdapter::class.java.simpleName
    private var oldMovieList = listOf<MoviesData>()
    private lateinit var onClickCallBack: (MoviesData) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerviewItemBinding.inflate(inflater)
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        oldMovieList[position].let { movieData ->
            movieData.poster_path.let {
                Log.d(className, "poster path is $it")
                Glide.with(holder.itemView).load(BuildConfig.IMAGE_BASE_URL.plus(it))
                    .into(holder.ivPoster)
            }

            onClickCallBack.let { callBackIt ->
                holder.itemView.setOnClickListener { callBackIt.invoke(movieData) }
            }
        }
    }

    inner class RecyclerViewHolder(@NonNull binding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val ivPoster = binding.ivPoster
    }

    override fun getItemCount(): Int {
        return oldMovieList.size
    }

    fun setUpCallBack(callBack: (MoviesData) -> Unit) {
        onClickCallBack = callBack
    }

    fun setData(newList: List<MoviesData>) {
        val diffUtils = DiffUtils(oldMovieList, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtils)
        oldMovieList = newList
        diffResult.dispatchUpdatesTo(this)
    }

}