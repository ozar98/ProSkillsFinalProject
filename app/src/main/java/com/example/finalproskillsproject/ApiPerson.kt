package com.example.finalproskillsproject

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class PersonRetrofit
    (val id:Int,val login:String, var fullName:String, val password:String, val phone:String,
    var birthDate:Long, val amount:Long)
data class TransactionRetrofit(
    val id:Int, val senderID: Int, val receiverID: Int, val senderType:String, val receiverType:String, val amount:Long, val status:String, val date:Long, val isSelected:Boolean, val bonus:Int )
data class CashBackRetrofit(
    val id:Int,
    val place:String,
    val percent:Int,
    val isSelected:Boolean
)
data class CardsRetrofit(
    val id:Int, val cardType:String,
    val fullName:String,
    val expiredDate:Long,
    val cardNumber:String,
    val cardsOwnerID:Int,
    val amount:Long,
    val bankName:String)


//2) Объявить интерфейс
interface Api {
    @GET("Person")
    suspend fun getPerson() : Call<Person>
    @GET("Transaction")
    suspend fun getTransaction() : Call<TransactionRetrofit>
    @GET("Transaction")
    suspend fun getListTransaction(): Call<List<TransactionRetrofit>>
    @GET("Cards")
    suspend fun getCard() : Call<CardsRetrofit>
    @GET("Cards")
    suspend fun getListCard(): Call<List<CardsRetrofit>>
    @GET("CashBack")
    suspend fun getCashBack() : Call<CashBackRetrofit>
    @GET("CashBack")
    suspend fun getListCashBack(): Call<List<CashBackRetrofit>>


}

//3) Сам ретрофит
class RetrofitClient {
    val retrofit = Retrofit
        .Builder()
        .baseUrl("https://http://localhost:5000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getPersonApi(): Api {
        return retrofit.create(Api::class.java)
    }
}
