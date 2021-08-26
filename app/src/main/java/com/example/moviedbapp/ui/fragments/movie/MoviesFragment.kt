package com.example.moviedbapp.ui.fragments.movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedbapp.R
import com.example.moviedbapp.adapter.movies.NowPlayingMoviesAdapter
import com.example.moviedbapp.adapter.movies.PopularMoviesAdapter
import com.example.moviedbapp.adapter.movies.TopRatedMoviesAdapter
import com.example.moviedbapp.adapter.movies.UpComingMoviesAdapter
import com.example.moviedbapp.databinding.MoviesFragmentBinding
import com.example.moviedbapp.ui.activities.DetailsActivity
import com.example.moviedbapp.utils.ZoomRecyclerLayout
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private val loggTag = MoviesFragment::class.java.simpleName
    private lateinit var binding: MoviesFragmentBinding
    private val viewModel: MoviesViewModel by viewModels()
    @Inject
    lateinit var popularMoviesAdapter: PopularMoviesAdapter
    @Inject
    lateinit var topRatedMoviesAdapter: TopRatedMoviesAdapter
    @Inject
    lateinit var upComingMoviesAdapter: UpComingMoviesAdapter
    @Inject
    lateinit var nowPlayingMoviesAdapter: NowPlayingMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(loggTag, "onCreateView")
        binding = DataBindingUtil.inflate(inflater, R.layout.movies_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(loggTag, "onStart")
        viewModel.makeApiCall()
        initGUI()
        applyListeners(this)
    }

    private fun initGUI() {
        Log.d(loggTag, "initGUI")
        binding.apply {
            rvPopular.adapter = popularMoviesAdapter
            rvPopular.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rvTopRated.adapter = topRatedMoviesAdapter
            rvTopRated.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rvUpComing.adapter = upComingMoviesAdapter
            rvUpComing.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rvNowPlaying.adapter = nowPlayingMoviesAdapter
            rvNowPlaying.layoutManager =
                context?.let { ZoomRecyclerLayout(it, LinearLayoutManager.HORIZONTAL, false) }
        }
    }

    private fun applyListeners(lifecycleOwner: LifecycleOwner) {

        popularMoviesAdapter.setUpCallBack {
            it.let { movieData ->
                Log.d(loggTag, "Movie Clicked Name is ${it.title}")
                context?.let { it ->
                    DetailsActivity.startDetailsActivity(
                        it,
                        movieData.id,
                        movieData.title,
                        movieData.backdrop_path,
                        movieData.release_date
                    )
                }
            }
        }

        viewModel.getPopularMoviesLiveData().observe(lifecycleOwner, {
            it.let {
                it.results.let { popularMovies ->
                    Log.d(loggTag, "getPopularMoviesLiveData -> ${popularMovies.size}")
                    popularMoviesAdapter.setData(popularMovies)
                }
            }
        })

        viewModel.getTopRatedMoviesLiveData().observe(lifecycleOwner, {
            it.let {
                it.results.let { popularMovies ->
                    Log.d(loggTag, "getTopRatedMoviesLiveData -> ${popularMovies.size}")
                    topRatedMoviesAdapter.setData(popularMovies)
                }
            }
        })

        viewModel.getUpComingMoviesLiveData().observe(lifecycleOwner, {
            it.let {
                it.results.let { popularMovies ->
                    Log.d(loggTag, "getUpComingMoviesLiveData -> ${popularMovies.size}")
                    upComingMoviesAdapter.setData(popularMovies)
                }
            }
        })

        viewModel.getNowPlayingMoviesLiveData().observe(lifecycleOwner, {
            it.let {
                it.results.let { popularMovies ->
                    Log.d(loggTag, "getNowPlayingMoviesLiveData -> ${popularMovies.size}")
                    nowPlayingMoviesAdapter.setData(popularMovies)
                }
            }
        })
    }

}