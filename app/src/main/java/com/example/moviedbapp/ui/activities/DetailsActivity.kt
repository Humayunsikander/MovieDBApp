package com.example.moviedbapp.ui.activities

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.moviedbapp.BuildConfig
import com.example.moviedbapp.R
import com.example.moviedbapp.adapter.movies.MovieGenresAdapter
import com.example.moviedbapp.databinding.ActivityDetailsBinding
import com.example.moviedbapp.network.models.movie.details.MovieDetailsResponse
import com.example.moviedbapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private val loggTag = DetailsActivity::class.java.simpleName
    private lateinit var binding: ActivityDetailsBinding
    private var movieId: Int = 0
    private lateinit var movieTitle: String
    private lateinit var movieBanner: String
    private lateinit var movieReleaseDate: String
    private val viewModel: DetailsViewModel by viewModels()
    var genAdapterMovie: MovieGenresAdapter = MovieGenresAdapter()

    private val calendar = Calendar.getInstance()
    private val year = calendar.get(Calendar.YEAR)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        initData()
        viewModel.getMovieDetails(movieId)
        initGUI()
        intiListener(this)
    }

    private fun initData() {
        intent.let {
            movieId = it.getIntExtra(Constants.MovieId.name, 0)
            movieTitle = it.getStringExtra(Constants.MovieTitle.name) as String
            movieBanner = it.getStringExtra(Constants.MovieBanner.name) as String
            movieReleaseDate = it.getStringExtra(Constants.MovieReleaseDate.name) as String
        }
    }

    private fun initGUI() {
        binding.apply {

            movieBanner.let {
                Glide.with(binding.root)
                    .load(BuildConfig.IMAGE_BASE_URL.plus(it))
                    .into(ivBanner)
            }

            movieTitle.let {
                tvTitle.text = title
            }

            movieReleaseDate.let {
                val stringArray = it.split("-")
                if (year == stringArray[0].toInt()) {
                    tvReleaseDateValue.setTextColor(Color.RED)
                }

                tvReleaseDateValue.text = it
            }

            rvGenres.adapter = genAdapterMovie
            rvGenres.layoutManager =
                LinearLayoutManager(this@DetailsActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun intiListener(lifecycleOwner: LifecycleOwner) {
        viewModel.getMovieDetailsLiveData().observe(lifecycleOwner, {
            updateUI(it)
        })
    }

    private fun updateUI(movieDetailsResponse: MovieDetailsResponse?) {
        binding.tvDesc.text = movieDetailsResponse?.overview
        binding.tvBudgetValue.text = movieDetailsResponse?.budget.toString()
        binding.tvDurationValue.text = movieDetailsResponse?.runtime.toString().plus("min")
        binding.tvReleaseDateValue.text = movieDetailsResponse?.release_date.toString()

        movieDetailsResponse?.genres?.let {
            if (it.isNotEmpty()) {
                genAdapterMovie.setData(it)
            }
        }
    }

    companion object {
        fun startDetailsActivity(
            context: Context,
            movieId: Int,
            movieTitle: String,
            movieBanner: String,
            movieReleaseDate: String
        ) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(Constants.MovieId.name, movieId)
            intent.putExtra(Constants.MovieTitle.name, movieTitle)
            intent.putExtra(Constants.MovieBanner.name, movieBanner)
            intent.putExtra(Constants.MovieReleaseDate.name, movieReleaseDate)
            context.startActivity(intent)
        }
    }
}