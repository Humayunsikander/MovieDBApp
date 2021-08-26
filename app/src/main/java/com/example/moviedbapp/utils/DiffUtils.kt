package com.example.moviedbapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.moviedbapp.network.models.movie.MoviesData

class DiffUtils(private val oldList: List<*>, private val newList: List<*>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return if (oldList[oldItemPosition] is MoviesData && newList[newItemPosition] is MoviesData) {
            val oldItem = oldList[oldItemPosition] as MoviesData
            val newItem = oldList[oldItemPosition] as MoviesData
            oldItem.id == newItem.id
        } else {
            false
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return if (oldList[oldItemPosition] is MoviesData && newList[newItemPosition] is MoviesData) {
            val oldItem = oldList[oldItemPosition] as MoviesData
            val newItem = oldList[oldItemPosition] as MoviesData
            oldItem == newItem
        } else {
            false
        }
    }
}