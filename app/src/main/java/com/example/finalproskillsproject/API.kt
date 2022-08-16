package com.example.finalproskillsproject

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class Balance(
    val id: Int,
    val phone: String,
    val balance: String,
    val cashBack: String,
)

//2) Объявить интерфейс
interface BalanceApi {
    @GET("balance")
    suspend fun getBalance() : Call<List<Balance>>


}

//3) Сам ретрофит
class RetrofitClient {
    val retrofit = Retrofit
        .Builder()
        .baseUrl("https://http://localhost:5000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getBalanceApi(): BalanceApi {
        return retrofit.create(BalanceApi::class.java)
    }
}