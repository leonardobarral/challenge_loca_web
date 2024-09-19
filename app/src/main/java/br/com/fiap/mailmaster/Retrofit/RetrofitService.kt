package br.com.fiap.mailmaster.Retrofit

import android.provider.ContactsContract.CommonDataKinds.Email
import br.com.fiap.mailmaster.dtos.UserLoginDto
import br.com.fiap.mailmaster.models.Message
import br.com.fiap.mailmaster.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {
    @POST("/authentication")
    fun getUserLoginToken(@Body userLoginDto : UserLoginDto): Call<User>

    @GET("/emails")
    fun getListEmails(): Call<List<Message>>

    @POST("/emails")
    fun setEmail(@Body message : Message): Unit

}