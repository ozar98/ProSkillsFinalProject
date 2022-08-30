package com.example.finalproskillsproject

import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

data class PersonRetrofit
    (val id:Int,val login:String,
     @SerializedName("fullname")
     var fullName:String, val password:String,
     @SerializedName("phonenumber")
     val phoneNumber:String,
    var birthDate:String, val amount:Long,val cashbackamount:Float)

data class TransactionRetrofit(
    val id:Int, val senderID: Int?, val receiverID: Int?, val sendertype:String, val receivertype:String,
    val cardreceiverID:Int?, val cardsenderID:Int?, val amount:Long, val status:String, val date:String, val cashBackID: Int?
)
data class CashBackRetrofit(
    val id:Int,
    val place:String,
    val percent:Int,
    val isSelected:Boolean
)
data class CardsRetrofit(
    val id:Int,
    val cardtype:String,
    val cardnumber:String,
    val validto:String,
    val amount:Long,
    val cardsownerid:Int,
    val bankname:String)

data class TransactionResponse(val id:Int,
                           val sendername: String?,
                           val receivername: String?,
                           val sendertype:String,
                           val receivertype:String,
                           val amount:Long,
                           val date:String,
                           val cashBackID: Int?
                           )


//2) Объявить интерфейс
interface Api {
    @GET("user{id}")
    suspend fun getPerson(@Path("id") id:Int) : Response<List<PersonRetrofit>>

    @GET("person{login}")
    fun getUser(@Path("login") login: String):Call<List<PersonRetrofit>>

    @PUT("user")
    suspend fun editUser(@Body person:PersonRetrofit): Response<PersonRetrofit>

    @PUT("userBalance")
    suspend fun updateBalance(@Body transactionRetrofit: TransactionRetrofit)
    @POST("user")
    fun createPerson(@Body personRetrofit: PersonRetrofit):Call<String>
    @GET("users")
    fun getPeople():Call<List<PersonRetrofit>>
    @GET("transaction")
    suspend fun getTransaction() : Call<TransactionRetrofit>

    @GET("listtrans")
    suspend fun getListTransactions():Response<List<TransactionResponse>>

    @GET("lastfivetr")
    suspend fun getListFiveTransactions():Response<List<TransactionResponse>>

    @GET("transaction{id}")
    suspend fun getTransactionByID(@Path("id") id:Int) : Response<List<TransactionResponse>>

    @GET("phone{number}")
    fun getReceiverIDbyNumber(@Path("number") phoneNumber: String): Call<List<PersonRetrofit>>


    @GET("card")
    suspend fun getCard(id:Int) : Call<CardsRetrofit>

    @GET("card{Number}")
    fun getCardByNumber(@Path("Number") cardNumber: String): Call<List<CardsRetrofit>>

    @GET("cards")
    suspend fun getListCard(): Response<List<CardsRetrofit>>
    @GET("cashBacks")
    suspend fun getListCashBack(): Response<List<CashBackRetrofit>>
    @POST("card")
    fun createCard(@Body card:CardsRetrofit): Call<String>



}

//3) Сам ретрофит
class RetrofitClient {
    val interceptor= HttpLoggingInterceptor().apply {
        level=HttpLoggingInterceptor.Level.BODY
    }
    private val retrofit: Retrofit = Retrofit
        .Builder()
        .client(OkHttpClient.Builder().addInterceptor(interceptor).build())
        .baseUrl("http://10.0.2.2:3000/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getPersonApi(): Api {
        return retrofit.create(Api::class.java)
    }



}
