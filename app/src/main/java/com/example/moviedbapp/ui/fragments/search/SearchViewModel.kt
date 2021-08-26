package com.example.moviedbapp.ui.fragments.search

import android.text.Editable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedbapp.network.models.movie.MoviesData
import com.example.moviedbapp.network.models.movie.PopularMoviesResponse
import com.example.moviedbapp.network.models.search.SearchMoviesResponse
import com.example.moviedbapp.repo.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {

    private val className = SearchViewModel::class.simpleName
    private var popularMoviesLiveData = MutableLiveData<ArrayList<MoviesData>>()
    private var moviesList = arrayListOf<MoviesData>()

    fun getPopularMoviesLiveData() = popularMoviesLiveData

    fun afterTextChanged(editable: Editable) {
        val searchWord = editable.toString()
        Log.d(className, "afterTextChanged $searchWord")
        filterData(searchWord)
    }

    private fun searchApi(searchWord: String) {
        Log.d(className, "searchApi -> $searchWord")
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(className, "searchApi -> launch")
            processResponseData(appRepository.searchMovie(searchWord))
        }
    }

    private fun filterData(keywork: String) {
        Log.d(className, "filterData -> $keywork")
        val filteredList = arrayListOf<MoviesData>()
        for (movie in moviesList) {
            movie.let {
                if (it.title.contains(
                        keywork,
                        ignoreCase = true
                    ) && !filteredList.contains(movie)
                ) {
                    filteredList.add(it)
                }
            }
        }

        if (!filteredList.isNullOrEmpty()) {
            popularMoviesLiveData.postValue(filteredList)
        } else {
            popularMoviesLiveData.postValue(moviesList)
        }
    }

    fun callWebApi() {
        viewModelScope.launch(Dispatchers.IO) {
            processResponseData(appRepository.getPopularMovies())
        }
    }

    private fun processResponseData(response: Response<*>?) {

        response?.let { supperIt ->
            if (supperIt.isSuccessful) {
                supperIt.body()?.let {
                    when (it) {
                        is PopularMoviesResponse -> {
                            moviesList.addAll(it.results)
                            popularMoviesLiveData.postValue(moviesList)
                        }
                        is SearchMoviesResponse -> {
                            moviesList.addAll(it.results)
                            popularMoviesLiveData.postValue(moviesList)
                        }
                    }
                } ?: run {
                    Log.d(className, "Api error message is ${supperIt.message()}")
                }
            } else {
                Log.d(className, "Api error message is ${supperIt.message()}")
            }
        }
    }

}