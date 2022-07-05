package com.enghamza.mviapp.data.api

import com.enghamza.mviapp.utils.StringConstants
import com.enghamza.mviapp.utils.StringConstants.apiKey
import com.enghamza.mviapp.utils.StringConstants.baseUrl
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitBuilder {

    companion object{

        fun getClient():ApiInterface{

        val retrofit=Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(createHttpLoggingInterceptor2())
            .build()

            return  retrofit.create(ApiInterface::class.java)

        }


    fun createHttpLoggingInterceptor2(): OkHttpClient? {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor { chain ->

            var request: Request = chain.request()
            val url: HttpUrl = request.url().newBuilder()
                .addQueryParameter("api_key", StringConstants.apiKey)
                .build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }.build()
    }
    }

}