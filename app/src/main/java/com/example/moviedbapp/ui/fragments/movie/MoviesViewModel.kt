package com.example.moviedbapp.ui.fragments.movie

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedbapp.network.models.movie.NowPlayingMoviesResponse
import com.example.moviedbapp.network.models.movie.PopularMoviesResponse
import com.example.moviedbapp.network.models.movie.TopRatedMoviesResponse
import com.example.moviedbapp.network.models.movie.UpComingMoviesResponse
import com.example.moviedbapp.repo.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {
    private var popularMoviesLiveData = MutableLiveData<PopularMoviesResponse>()
    private var nowPlayingMoviesLiveData = MutableLiveData<NowPlayingMoviesResponse>()
    private var topRatedMoviesLiveData = MutableLiveData<TopRatedMoviesResponse>()
    private var upComingMoviesLiveData = MutableLiveData<UpComingMoviesResponse>()

    fun getPopularMoviesLiveData() = popularMoviesLiveData
    fun getTopRatedMoviesLiveData() = topRatedMoviesLiveData
    fun getUpComingMoviesLiveData() = upComingMoviesLiveData
    fun getNowPlayingMoviesLiveData() = nowPlayingMoviesLiveData

    fun makeApiCall() {
        Log.d("TAG", "makeApiCall")
        viewModelScope.launch(Dispatchers.IO) {

            async {
                processResponseData(appRepository.getNowPlayingMovies())
            }

            async {
                processResponseData(appRepository.getPopularMovies())
            }

            async {
                processResponseData(appRepository.getTopRatedMovies())
            }

            async {
                processResponseData(appRepository.getUpComingMovies())
            }

        }

    }

    private fun processResponseData(response: Response<*>?) {

        response?.let { supperIt ->
            if (supperIt.isSuccessful) {
                supperIt.body()?.let {
                    when (it) {
                        is PopularMoviesResponse -> {
                            popularMoviesLiveData.postValue(it)
                        }
                        is TopRatedMoviesResponse -> {
                            topRatedMoviesLiveData.postValue(it)
                        }
                        is UpComingMoviesResponse -> {
                            upComingMoviesLiveData.postValue(it)
                        }
                        is NowPlayingMoviesResponse -> {
                            nowPlayingMoviesLiveData.postValue(it)
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