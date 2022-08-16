package com.example.finalproskillsproject

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.util.*

class MainViewModel(application: Application) : AndroidViewModel(application){
    private val _balanceliveData = MutableLiveData<List<Balance?>>(null)
    val balanceliveData: LiveData<List<Balance?>> =_balanceliveData

    //progress
    private val _progressLiveData= MutableLiveData<Boolean>(false)
    val progressLiveData: LiveData<Boolean> =_progressLiveData
    // error
    private val _errorLiveData= MutableLiveData<String?>(null)
    val errorLiveData: LiveData<String?> =_errorLiveData


    val liveData= MediatorLiveData<List<Balance>>()
    private val retrofit = RetrofitClient()

    private suspend fun requestCoffee() = withContext(Dispatchers.IO) {
        _progressLiveData.postValue(true)
        try {
            val response: Response<Balance> = retrofit.getBalanceApi().getBalance()
            if (response.isSuccessful) {
                val balance = response.body()
                _balanceLiveData.postValue(balance)
                _errorLiveData.postValue(null)
            } else {
                _coffeeLiveData.postValue(null)
                _errorLiveData.postValue("Что-то не так...")
            }
        } catch (e: ConnectException) {
            _coffeeLiveData.postValue(null)
            _errorLiveData.postValue("Нет сети!")
        } catch (e: SocketTimeoutException) {
            _coffeeLiveData.postValue(null)
            _errorLiveData.postValue("Превышено время ожидания!")
        } catch (e: Exception) {
            _coffeeLiveData.postValue(null)
            _errorLiveData.postValue("Что-то не так!")
        }
        _progressLiveData.postValue(false)
    }

    fun getBalanceInfo():String{
        return ""
    }
    fun getCashBack():String{
        return ""
    }
    fun getUserInfo():String{
        return ""
    }
    fun getBalanceList():List<Balance>{
        return listOf()
    }
    fun getCardsList():List<Cards>{
        return listOf()
    }
    fun getHistoryList():List<History>{
        return listOf()
    }
}

class RegistrationViewModel(application: Application) : AndroidViewModel(application){
    fun getBirthDate(year:Int, month:Int, day:Int):String{
        return "${if (day < 10) "0$day" else day}.${if ((month + 1) < 10) "0${month + 1}" else month + 1}.$year"
    }
    fun today(): Long {
        return Calendar.getInstance().timeInMillis - (Calendar.getInstance().timeInMillis % (3600 * 24000))
    }
    fun registerPerson() {

    }

}

class HistoryViewModel(application: Application):AndroidViewModel(application){
    fun getHistoryList(id:Int):List<History>{
        return listOf()
    }
}
class ChangeProfileViewModel(application: Application):AndroidViewModel(application){
    fun getBirthDate(year:Int, month:Int, day:Int):String{
        return "${if (day < 10) "0$day" else day}.${if ((month + 1) < 10) "0${month + 1}" else month + 1}.$year"
    }

}
