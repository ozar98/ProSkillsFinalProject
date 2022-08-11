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

//class CardsAdapter:ListAdapter<CardInfo, CardsAdapter.CardsViewHolder> (CardsDiffUtil()) {
//    var onItemClick:((Int)->Unit)?=null
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder {
//        return CardsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cards_rv,parent,false))
//    }
//
//    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
//        if (holder.type.text=="Visa"){
//            holder.icon.setImageResource(R.drawable.ic_icon_mastercard_logo)
//        }else if(holder.type.text=="National"){
//            holder.icon.setBackgroundResource(R.drawable.ic_nationalcard)
//        }
//    }
//
//
//    //DiffUtil
//    private class CardsDiffUtil:DiffUtil.ItemCallback<CardsInfo>(){
//        override fun areItemsTheSame(oldItem: CardsInfo, newItem: CardsInfo): Boolean {
//            return oldItem.id==newItem.id
//        }
//
//        override fun areContentsTheSame(oldItem: CardsInfo, newItem: CardsInfo): Boolean {
//            return oldItem==newItem
//        }
//
//    }
//
//    //ViewHolder
//    inner class CardsViewHolder(val view:View):RecyclerView.ViewHolder(view){
//        val type:TextView=view.findViewById(R.id.card_type)
//        val bank_digits:TextView=view.findViewById(R.id.bank_and_last_digits)
//        val amount:TextView=view.findViewById(R.id.card_amount)
//        val card_info:TextView=view.findViewById(R.id.card_info)
//        val icon:ImageButton=view.findViewById(R.id.card_icon)
//        init {
//            view.setOnClickListener {
//
//            }
//        }
//    }
//
//
//
//}
