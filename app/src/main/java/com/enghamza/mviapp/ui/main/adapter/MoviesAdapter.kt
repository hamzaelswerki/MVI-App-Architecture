package com.enghamza.mviapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.enghamza.mviapp.BR
import com.enghamza.mviapp.databinding.ItemMovieRecyclerBinding
import com.enghamza.mviapp.ui.main.model.newMovie.Movie

class MoviesAdapter (var listMovie: ArrayList<Movie>):RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(ItemMovieRecyclerBinding.inflate(LayoutInflater.from(parent.context) ,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         holder.bind(listMovie[position])
    }

    override fun getItemCount(): Int {
        return  listMovie.size
    }
    fun addData( list: List<Movie>){
         listMovie.addAll(list)
    }

    inner class ViewHolder(var itemRow: ItemMovieRecyclerBinding): RecyclerView.ViewHolder(itemRow.root) {
        fun bind(movie: Movie){
         itemRow.setVariable(BR.movie,movie)
        }
    }


}