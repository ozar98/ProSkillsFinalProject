package com.example.finalproskillsproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


class CashbacksAdapter: ListAdapter<CashBackRetrofit, CashbacksAdapter.CashbacksViewHolder>(CashBacksDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CashbacksViewHolder {
        return CashbacksViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cashback_rv,parent,false))
    }

    override fun onBindViewHolder(holder: CashbacksViewHolder, position: Int) {
        holder.place.text=getItem(position).place
        holder.percent.text=getItem(position).percent.toString()+"%"
    }

    inner class CashbacksViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val place:TextView=view.findViewById(R.id.place)
        val percent:TextView=view.findViewById(R.id.percent)

        init {

        }
    }
    private class CashBacksDiffUtil: DiffUtil.ItemCallback<CashBackRetrofit>(){
        override fun areItemsTheSame(oldItem: CashBackRetrofit, newItem: CashBackRetrofit): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CashBackRetrofit, newItem: CashBackRetrofit): Boolean {
            return oldItem== newItem
        }

    }
}