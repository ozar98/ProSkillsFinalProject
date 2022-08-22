package com.example.finalproskillsproject

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView

class CardsAdapter:ListAdapter<Cards,CardsAdapter.CardsViewHolder>(CardsDiffUtil()){



    inner class CardsViewHolder(val view: View):RecyclerView.ViewHolder(view){
        val type:TextView=view.findViewById(R.id.card_type)
        val icon:ImageButton=view.findViewById(R.id.card_icon)
        val bankName:TextView=view.findViewById(R.id.bank_and_last_digits)
        val amount:TextView=view.findViewById(R.id.card_amount)
        val cardNumber:TextView=view.findViewById(R.id.card_info)

        init {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder=
        CardsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cards_rv,parent,false))


    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
        if (getItem(position).cardType=="Visa"){
            holder.icon.setImageResource(R.drawable.ic_icon_mastercard_logo)
        }else{
            holder.icon.setImageResource(R.drawable.ic_nationalcard)
        }
        holder.type.text=getItem(position).cardType
        holder.amount.text=getItem(position).amountCard
        holder.bankName.text=getItem(position).bankName
        holder.cardNumber.text=getItem(position).cardNumber
    }

    private class CardsDiffUtil:DiffUtil.ItemCallback<Cards>(){
        override fun areItemsTheSame(oldItem: Cards, newItem: Cards): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Cards, newItem: Cards): Boolean {
            return oldItem.isSelected == newItem.isSelected
        }
    }
}
