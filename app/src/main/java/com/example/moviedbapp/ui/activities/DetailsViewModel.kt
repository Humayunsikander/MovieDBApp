package com.example.moviedbapp.ui.activities

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedbapp.network.models.movie.details.MovieDetailsResponse
import com.example.moviedbapp.repo.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {

    private var movieDetailsLiveData = MutableLiveData<MovieDetailsResponse>()

    fun getMovieDetailsLiveData() = movieDetailsLiveData

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            processResponseData(appRepository.getMovieDetails(movieId))
        }
    }

    private fun processResponseData(response: Response<*>?) {

        response?.let { supperIt ->
            if (supperIt.isSuccessful) {
                supperIt.body()?.let {
                    when (it) {
                        is MovieDetailsResponse -> {
                            movieDetailsLiveData.postValue(it)
                        }
                    }
                } ?: run {
                    Log.d("TAG", "Api error message is ${supperIt.message()}")
                }
            } else {
                Log.d("TAG", "Api error message is ${supperIt.message()}")
            }
        }
    }

}