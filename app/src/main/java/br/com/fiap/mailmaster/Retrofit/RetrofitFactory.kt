package br.com.fiap.mailmaster.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {
//    private val userUrl = "http://localhost:3000"
    private val userUrl = "http://192.168.18.7:3000"

    private val userAuteticationFactory = Retrofit
        .Builder()
        .baseUrl(userUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getRetrofiteService(): RetrofitService{
        return userAuteticationFactory.create(RetrofitService::class.java)
    }




}