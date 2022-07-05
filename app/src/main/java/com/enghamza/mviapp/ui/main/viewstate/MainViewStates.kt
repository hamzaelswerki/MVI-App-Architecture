package com.enghamza.mviapp.ui.main.viewstate

import com.enghamza.mviapp.ui.main.model.newMovie.Movie

sealed  class MainViewStates {

    object Idle:MainViewStates()
    object Loading:MainViewStates()
    data class Movies(val list: List<Movie>):MainViewStates()

    data class Error(val message:String):MainViewStates()
}