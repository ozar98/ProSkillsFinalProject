package com.example.finalproskillsproject

class Person(val login:String, val fullName:String, val password:String, val phone:String) {
}
class HistoryInfo(val id:Int,val fullName: String, val date:Long, val amount: Int, val status:String, var isSelected: Boolean, val paymentMethod:String ){}

class Cards(val id: Int,val cardType:String, val bankName:String, val amountCard:String, val cardNumber:String, var isSelected:Boolean){
}
class CashBacks(val id:Int, val place:String, val percent:Int, val logo:Int, var isSelected: Boolean)

