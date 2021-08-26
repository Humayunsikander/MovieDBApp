package com.example.moviedbapp.ui.fragments.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviedbapp.R
import com.example.moviedbapp.adapter.SearchRecyclerAdapter
import com.example.moviedbapp.databinding.SearchFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private val loggTag = SearchFragment::class.java.simpleName

    private lateinit var binding: SearchFragmentBinding
    private val viewModel: SearchViewModel by viewModels()
    @Inject
    lateinit var searchRecyclerAdapter: SearchRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(loggTag, "onCreateView")
        binding = DataBindingUtil.inflate(inflater, R.layout.search_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(loggTag, "onViewCreated")
        binding.searchViewModel = viewModel
        viewModel.callWebApi()
        intGUI()
        applyListener(this)
    }

    private fun intGUI() {
        Log.d(loggTag, "intGUI")
        binding.apply {
            rvSearchPopular.adapter = searchRecyclerAdapter
            rvSearchPopular.layoutManager = GridLayoutManager(context, 2)

        }
    }

    private fun applyListener(lifecycleOwner: LifecycleOwner) {
        Log.d(loggTag, "applyListener")
        viewModel.getPopularMoviesLiveData().observe(lifecycleOwner, {
            it.let {
                Log.d(loggTag, "getPopularMoviesLiveData -> ${it.size}")
                if (it.size > 0) {
                    searchRecyclerAdapter.sendMoviesToAdapter(it)
                }
            }
        })
    }

}