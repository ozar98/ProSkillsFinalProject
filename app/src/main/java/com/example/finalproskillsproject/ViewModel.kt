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


    fun getBalanceInfo():String{
        return "1200 Somoni"
    }
    fun getCashBack():String{
        return "5.62 Somoni"
    }
    fun getUserInfo():String{
        return ""
    }
    fun getNumber():String{
        return "+992 935012824"
    }
//    fun getBalanceList():List<Balance>{
//        return listOf()
//    }
    fun getCardsList():List<Cards>{
        return listOf()
    }
    fun getHistoryList():List<HistoryInfo>{
        return listOf()
    }
    fun getCashBacksList():List<CashBacks>{
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
//    fun getHistoryList(id:Int):List<History>{
//        return listOf()
//    }
}
class ChangeProfileViewModel(application: Application):AndroidViewModel(application){
    fun getBirthDate(year:Int, month:Int, day:Int):String{
        return "${if (day < 10) "0$day" else day}.${if ((month + 1) < 10) "0${month + 1}" else month + 1}.$year"
    }

}

class FragmentBalanceIncreaseViewModel(application: Application):AndroidViewModel(application){
    fun increaseBalance(amount:Long, cardNumber:String){

    }
    fun transferedSuccessfully():Boolean{
        return true
    }
}

class TransactionViewModel(application: Application):AndroidViewModel(application){
    fun sendToNumber(amount:Long, number:String){

    }
    fun updatePhoneBalance(){}
}
class AddCardViewModel(application: Application):AndroidViewModel(application){
    fun getBirthDate(year:Int, month:Int, day:Int):String{
        return "${if (day < 10) "0$day" else day}.${if ((month + 1) < 10) "0${month + 1}" else month + 1}.$year"
    }
}
