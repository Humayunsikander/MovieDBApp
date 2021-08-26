package com.example.moviedbapp.ui.fragments.tv

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedbapp.network.models.tv.popular.PopularTvResponse
import com.example.moviedbapp.repo.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class TVViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {
    private var popularTvLiveData = MutableLiveData<PopularTvResponse>()

    fun getPopularTvLiveData() = popularTvLiveData

    fun makeApiCall() {
        Log.d("TAG", "makeApiCall")
        viewModelScope.launch(Dispatchers.IO) {

            async {
                processResponseData(appRepository.getNowPlayingMovies())
            }

        }

    }

    private fun processResponseData(response: Response<*>?) {

        response?.let { supperIt ->
            if (supperIt.isSuccessful) {
                supperIt.body()?.let {
                    when (it) {
                        is PopularTvResponse -> {
                            popularTvLiveData.postValue(it)
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