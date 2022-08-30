package com.example.finalproskillsproject

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.util.*

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val retrofit = RetrofitClient()
    private var _livedata = MutableLiveData<Boolean>()
    val liveData = _livedata!!
    var id: Int = 0


    fun verifyPerson(login: String, password: String) {
        retrofit.getPersonApi().getUser(login).enqueue(object : Callback<List<PersonRetrofit>> {
            override fun onResponse(
                call: Call<List<PersonRetrofit>>,
                response: Response<List<PersonRetrofit>>
            ) {
                if (response.isSuccessful && response.body() != null && response.body()!!
                        .isNotEmpty() && response.body()!![0].login == login && response.body()!![0].password == password
                ) {
                    Log.d("TAG_TEST", "in the system")
                    id = response.body()!![0].id
                    _livedata.postValue(true)
                } else {
                    Log.d("TAG_TEST", "is not Successful ${response.code()}")

                    _livedata.postValue(false)
                }
            }

            override fun onFailure(call: Call<List<PersonRetrofit>>, t: Throwable) {
                Log.d("TAG_TEST", t.message.toString())

                _livedata.postValue(false)
            }
        })

    }
}


class MainViewModel(application: Application) : AndroidViewModel(application) {
    var currentFragment = 0

    private val retrofit = RetrofitClient()

    var personName: String=""
    var personID: Int=0

    var personClass: PersonRetrofit? =null

    fun getBirthDate(year: Int, month: Int, day: Int): String {
        return "${if (day < 10) "0$day" else day}.${if ((month + 1) < 10) "0${month + 1}" else month + 1}.$year"
    }

    // person
    private val _personLiveData = MutableLiveData<PersonRetrofit?>(null)
    val personLiveData: LiveData<PersonRetrofit?> = _personLiveData

    private val _cardLiveData = MutableLiveData<Int>()
    val cardLiveData: LiveData<Int> = _cardLiveData

    // progress
    private val _progressLiveData = MutableLiveData<Boolean>(false)
    val progressLiveData: LiveData<Boolean> = _progressLiveData

    // error
    private val _errorLiveData = MutableLiveData<String?>(null)
    val errorLiveData: LiveData<String?> = _errorLiveData

    fun getPerson(id: Int) {
        viewModelScope.launch {
            requestPerson(id)
        }
    }

    private suspend fun requestPerson(id: Int) = withContext(Dispatchers.IO) {
        _progressLiveData.postValue(true)
        try {
            val response = retrofit.getPersonApi().getPerson(id)
            if (response.isSuccessful && response.body() != null && response.body()!!
                    .isNotEmpty()
            ) {
                val person = response.body()!![0]
                personID = person.id
                personName= person.fullName
                personClass=person

                _personLiveData.postValue(person)
                _errorLiveData.postValue(null)
            } else {
                _personLiveData.postValue(null)
                _errorLiveData.postValue("Что-то не так...")
            }
        } catch (e: ConnectException) {
            _personLiveData.postValue(null)
            _errorLiveData.postValue("Нет сети!")
            Log.d("TAG_TEst", "Нет сети!")
        } catch (e: SocketTimeoutException) {
            _personLiveData.postValue(null)
            Log.d("TAG_TEst", "Превышено время ожидания!")
            _errorLiveData.postValue("Превышено время ожидания!")
        } catch (e: Exception) {
            Log.d("TAG_TEst", e.message.toString())
            _personLiveData.postValue(null)
            _errorLiveData.postValue("Что-то не так!")
        }
        _progressLiveData.postValue(false)
    }

    // cards
    private val _cardsLiveData = MutableLiveData<List<CardsRetrofit>?>(null)
    val cardsLiveData: MutableLiveData<List<CardsRetrofit>?> = _cardsLiveData
    fun getCards() {
        viewModelScope.launch {
            requestCards()
        }
    }

    private suspend fun requestCards() = withContext(Dispatchers.IO) {
        _progressLiveData.postValue(true)
        try {
            val response = retrofit.getPersonApi().getListCard()
            if (response.isSuccessful && response.body() != null && response.body()!!
                    .isNotEmpty()
            ) {
                val cards = response.body()!!
                _cardsLiveData.postValue(cards)
                _errorLiveData.postValue(null)
            } else {
                _cardsLiveData.postValue(null)
                _errorLiveData.postValue("Что-то не так...")
            }
        } catch (e: ConnectException) {
            _cardsLiveData.postValue(null)
            _errorLiveData.postValue("Нет сети!")
            Log.d("TAG_TEst", "Нет сети!")
        } catch (e: SocketTimeoutException) {
            _cardsLiveData.postValue(null)
            Log.d("TAG_TEst", "Превышено время ожидания!")
            _errorLiveData.postValue("Превышено время ожидания!")
        } catch (e: Exception) {
            Log.d("TAG_TEst", e.message.toString())
            _cardsLiveData.postValue(null)
            _errorLiveData.postValue("Что-то не так!")
        }
        _progressLiveData.postValue(false)
    }

    //transactions
    private val _transactionLiveData = MutableLiveData<List<TransactionResponse>?>(null)
    val transactionLiveData: MutableLiveData<List<TransactionResponse>?> = _transactionLiveData
    fun getTransactions() {
        viewModelScope.launch {
            requestTransactions()
        }
    }

    private suspend fun requestTransactions() = withContext(Dispatchers.IO) {
        _progressLiveData.postValue(true)
        try {
            val response = retrofit.getPersonApi().getListFiveTransactions()
            if (response.isSuccessful && response.body() != null && response.body()!!
                    .isNotEmpty()
            ) {
                val transactions = response.body()!!
                _transactionLiveData.postValue(transactions)
                _errorLiveData.postValue(null)
            } else {
                _transactionLiveData.postValue(null)
                _errorLiveData.postValue("Что-то не так...")
            }
        } catch (e: ConnectException) {
            _transactionLiveData.postValue(null)
            _errorLiveData.postValue("Нет сети!")
            Log.d("TAG_TEst", "Нет сети!")
        } catch (e: SocketTimeoutException) {
            _transactionLiveData.postValue(null)
            Log.d("TAG_TEst", "Превышено время ожидания!")
            _errorLiveData.postValue("Превышено время ожидания!")
        } catch (e: Exception) {
            Log.d("TAG_TEst", e.message.toString())
            _transactionLiveData.postValue(null)
            _errorLiveData.postValue("Что-то не так!")
        }
        _progressLiveData.postValue(false)
    }
    //////////////
    //Transaction By ID

    private val _transactionIDLiveData = MutableLiveData<TransactionResponse?>(null)
    val transactionIDLiveData: LiveData<TransactionResponse?> = _transactionIDLiveData


    fun getTransactionByID(id: Int) {
        viewModelScope.launch {
            requestTransactionByID(id)
        }
    }

    private suspend fun requestTransactionByID(id: Int) = withContext(Dispatchers.IO) {
        _progressLiveData.postValue(true)
        try {

            val response = retrofit.getPersonApi().getTransactionByID(id)
            if (response.isSuccessful && response.body() != null && response.body()!!
                    .isNotEmpty()
            ) {
                val transaction = response.body()!![0]
                _transactionIDLiveData.postValue(transaction)
                _errorLiveData.postValue(null)
            } else {
                _personLiveData.postValue(null)
                _errorLiveData.postValue("Что-то не так...")
            }
        } catch (e: ConnectException) {
            _personLiveData.postValue(null)
            _errorLiveData.postValue("Нет сети!")
            Log.d("TAG_TEst", "Нет сети!")
        } catch (e: SocketTimeoutException) {
            _personLiveData.postValue(null)
            Log.d("TAG_TEst", "Превышено время ожидания!")
            _errorLiveData.postValue("Превышено время ожидания!")
        } catch (e: Exception) {
            Log.d("TAG_TEst", e.message.toString())
            _personLiveData.postValue(null)
            _errorLiveData.postValue("Что-то не так!")
        }
        _progressLiveData.postValue(false)
    }
    ///////////////////


    ///////////Cashbacks

    private val _cashbackLiveData = MutableLiveData<List<CashBackRetrofit>?>(null)
    val cashbackLiveData: MutableLiveData<List<CashBackRetrofit>?> = _cashbackLiveData
    fun getCashbacks() {
        viewModelScope.launch {
            requestCashbacks()
        }
    }

    private suspend fun requestCashbacks() = withContext(Dispatchers.IO) {
        _progressLiveData.postValue(true)
        try {
            val response = retrofit.getPersonApi().getListCashBack()
            if (response.isSuccessful && response.body() != null && response.body()!!
                    .isNotEmpty()
            ) {
                val cashbacks = response.body()!!
                _cashbackLiveData.postValue(cashbacks)
                _errorLiveData.postValue(null)
            } else {
                _cashbackLiveData.postValue(null)
                _errorLiveData.postValue("Что-то не так...")
            }
        } catch (e: ConnectException) {
            _cashbackLiveData.postValue(null)
            _errorLiveData.postValue("Нет сети!")
            Log.d("TAG_TEst", "Нет сети!")
        } catch (e: SocketTimeoutException) {
            _cashbackLiveData.postValue(null)
            Log.d("TAG_TEst", "Превышено время ожидания!")
            _errorLiveData.postValue("Превышено время ожидания!")
        } catch (e: Exception) {
            Log.d("TAG_TEst", e.message.toString())
            _cashbackLiveData.postValue(null)
            _errorLiveData.postValue("Что-то не так!")
        }
        _progressLiveData.postValue(false)
    }


    /////////////////////////////


    fun getTodayDate(): String {
        val day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        val month = Calendar.getInstance().get(Calendar.MONTH)
        val year = Calendar.getInstance().get(Calendar.YEAR)
        return "${if (day < 10) "0$day" else day}.${if ((month + 1) < 10) "0${month + 1}" else month + 1}.$year"
    }

    fun increaseBalance(id: Int, amount: Long, cardID: Int) {
        val transaction =
            TransactionRetrofit(
                1,
                id,
                id,
                "Card",
                "phone",
                null,
                cardID,
                amount,
                "sent",
                getTodayDate(),
                null
            )
        viewModelScope.launch(Dispatchers.IO) {
            retrofit.getPersonApi().updateBalance(transaction)
        }

    }

    fun getcardID(cardNumber: String) {
        retrofit.getPersonApi().getCardByNumber(cardNumber)
            .enqueue(object : Callback<List<CardsRetrofit>> {
                override fun onResponse(
                    call: Call<List<CardsRetrofit>>,
                    response: Response<List<CardsRetrofit>>
                ) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            _cardLiveData.postValue(response.body()!![0].id)
                            Log.d("TAG_TESt", response.body()!!.toString())
                        }
                    } else {
                        Log.d("TAG_Card", response.message().toString())
                    }
                }

                override fun onFailure(call: Call<List<CardsRetrofit>>, t: Throwable) {
                    Log.d("TAG_Card", t.message.toString())
                }

            })

    }

    fun changeProfile(name:String,date:String){
        if (personClass!=null) {
            personClass?.fullName = name
            personClass?.birthDate = date

            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val response=retrofit.getPersonApi().editUser(personClass!!)
                    if (response.isSuccessful){
                        _personLiveData.postValue(response.body())
                    }
                }catch (e:Exception){

                }

            }
        }
    }


}

class RegistrationViewModel(application: Application) : AndroidViewModel(application) {
    private val retrofit = RetrofitClient()
    fun getBirthDate(year: Int, month: Int, day: Int): String {
        return "${if (day < 10) "0$day" else day}.${if ((month + 1) < 10) "0${month + 1}" else month + 1}.$year"
    }

    fun today(): Long {
        return Calendar.getInstance().timeInMillis - (Calendar.getInstance().timeInMillis % (3600 * 24000))
    }

    fun registerPerson(personRetrofit: PersonRetrofit) {
        retrofit.getPersonApi().createPerson(personRetrofit).enqueue(object : Callback<String> {
            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {
                if (response.isSuccessful) {

                    //...
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })


    }

}

class HistoryViewModel(application: Application) : AndroidViewModel(application) {
    private val retrofit = RetrofitClient()

    //transactions
    private val _transactionLiveData = MutableLiveData<List<TransactionResponse>?>(null)
    val transactionLiveData: MutableLiveData<List<TransactionResponse>?> = _transactionLiveData

    // progress
    private val _progressLiveData = MutableLiveData<Boolean>(false)
    val progressLiveData: LiveData<Boolean> = _progressLiveData

    // error
    private val _errorLiveData = MutableLiveData<String?>(null)
    val errorLiveData: LiveData<String?> = _errorLiveData
    fun getTransactions() {
        viewModelScope.launch {
            requestTransactions()
        }
    }

    private suspend fun requestTransactions() = withContext(Dispatchers.IO) {
        _progressLiveData.postValue(true)
        try {
            val response = retrofit.getPersonApi().getListTransactions()
            if (response.isSuccessful && response.body() != null && response.body()!!
                    .isNotEmpty()
            ) {
                val transactions = response.body()!!
                _transactionLiveData.postValue(transactions)
                _errorLiveData.postValue(null)
            } else {
                _transactionLiveData.postValue(null)
                _errorLiveData.postValue("Что-то не так...")
            }
        } catch (e: ConnectException) {
            _transactionLiveData.postValue(null)
            _errorLiveData.postValue("Нет сети!")
            Log.d("TAG_TEst", "Нет сети!")
        } catch (e: SocketTimeoutException) {
            _transactionLiveData.postValue(null)
            Log.d("TAG_TEst", "Превышено время ожидания!")
            _errorLiveData.postValue("Превышено время ожидания!")
        } catch (e: Exception) {
            Log.d("TAG_TEst", e.message.toString())
            _transactionLiveData.postValue(null)
            _errorLiveData.postValue("Что-то не так!")
        }
        _progressLiveData.postValue(false)
    }
    //////////////
}

class TransactionViewModel(application: Application) : AndroidViewModel(application) {
    private val retrofit = RetrofitClient()
    private val _receiverIDLiveData = MutableLiveData<Int>()
    val receiverIDLiveData: LiveData<Int> = _receiverIDLiveData

    fun sendToNumber(id: Int, amount: Long, receiverNumber: String, receiverID: Int) {
        val transaction = TransactionRetrofit(
            1,
            id,
            receiverID,
            "phone",
            "phone",
            null,
            null,
            amount,
            "sent",
            getTodayDate(),
            null
        )
        viewModelScope.launch(Dispatchers.IO) {
            retrofit.getPersonApi().updateBalance(transaction)
        }

    }

    fun getTodayDate(): String {
        val day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        val month = Calendar.getInstance().get(Calendar.MONTH)
        val year = Calendar.getInstance().get(Calendar.YEAR)
        return "${if (day < 10) "0$day" else day}.${if ((month + 1) < 10) "0${month + 1}" else month + 1}.$year"
    }

    fun getreceiverID(receiverNumber: String) {
        retrofit.getPersonApi().getReceiverIDbyNumber(receiverNumber)
            .enqueue(object : Callback<List<PersonRetrofit>> {
                override fun onResponse(
                    call: Call<List<PersonRetrofit>>,
                    response: Response<List<PersonRetrofit>>
                ) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            if (response.body()!!.isEmpty()) {
                                Log.d("TAG_Card", "onResponse: No such number")
                            } else {
                                _receiverIDLiveData.postValue(response.body()!![0].id)
                                Log.d("TAG_TESt", response.body()!!.toString())
                            }
                        }
                    } else {
                        Log.d("TAG_Card", response.message().toString())
                    }
                }

                override fun onFailure(call: Call<List<PersonRetrofit>>, t: Throwable) {
                    Log.d("TAG_Card", t.message.toString())
                }

            })

    }
}


class AddCardViewModel(application: Application) : AndroidViewModel(application) {
    private val retrofit = RetrofitClient()
    fun getBirthDate(year: Int, month: Int, day: Int): String {
        return "${if (day < 10) "0$day" else day}.${if ((month + 1) < 10) "0${month + 1}" else month + 1}.$year"
    }

    fun addCard(cardsRetrofit: CardsRetrofit) {

        retrofit.getPersonApi().createCard(cardsRetrofit).enqueue(object : Callback<String> {
            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {
                if (response.isSuccessful) {
                    Log.d("TAG_Card", "card added")

                    //...
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("TAG_Card", t.message.toString())
            }

        })

    }
}
