package com.enghamza.mviapp.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enghamza.mviapp.data.api.ApiInterface
import com.enghamza.mviapp.ui.main.intent.MainIntent
import com.enghamza.mviapp.ui.main.viewstate.MainViewStates
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MainViewModel(val apiInterface: ApiInterface) :ViewModel() {


    val moviesChanelIntents=Channel<MainIntent>(Channel.UNLIMITED)
    private val _states= MutableStateFlow<MainViewStates>(MainViewStates.Idle)
    val states:MutableStateFlow<MainViewStates> get() = _states

    private val _statesPopualrMovies= MutableStateFlow<MainViewStates>(MainViewStates.Idle)
    val statesPopual:MutableStateFlow<MainViewStates> get()=_states

    init {
        handleIntents()
    }

  fun  handleIntents(){
      viewModelScope.launch {
           moviesChanelIntents.consumeAsFlow().collect {
               when(it){
                   is MainIntent.GetMovies->getMovies()
               }
           }
      }

  }


    fun getMovies(){
            viewModelScope.launch {
                _states.value=MainViewStates.Loading
                _states.value=try {
                        MainViewStates.Movies(apiInterface.getMovieByType("top_rated",1)
                            .body()!!.results)
                }catch (e:Exception){
                        MainViewStates.Error(e.message!!)
                }
            }


    }


}