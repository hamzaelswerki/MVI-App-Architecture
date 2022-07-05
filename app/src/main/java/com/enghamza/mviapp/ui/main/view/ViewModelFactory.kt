package com.enghamza.mviapp.ui.main.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.enghamza.mviapp.data.api.ApiInterface
import com.enghamza.mviapp.ui.main.viewmodel.MainViewModel


class MainViewModelFactory(private val apiInterface: ApiInterface) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(apiInterface) as T
    }
}