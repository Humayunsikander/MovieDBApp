package com.example.moviedbapp.ui.fragments.tv

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedbapp.R
import com.example.moviedbapp.databinding.TVFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TVFragment : Fragment() {

    private lateinit var binding: TVFragmentBinding
    private val viewModel: TVViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.t_v_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Fragment", "onStart")
        viewModel.makeApiCall()
    }

    override fun onResume() {
        super.onResume()
        binding.apply {
            rvOnTheAir.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//            rvOnTheAir.adapter = topRatedMoviesAdapter
        }
    }

}