package com.enghamza.mviapp.data.api

import com.enghamza.mviapp.ui.main.model.newMovie.MoveiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

        @GET("movie/{type}")
      suspend fun getMovieByType(
        @Path("type")type:String,
        @Query("page")page:Int):Response<MoveiResponse>



    }

