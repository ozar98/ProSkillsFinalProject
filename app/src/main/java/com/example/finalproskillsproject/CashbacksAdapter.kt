package com.example.finalproskillsproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


class CashbacksAdapter: ListAdapter<CashBacks, CashbacksAdapter.CashbacksViewHolder>(CashBacksDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CashbacksViewHolder {
        return CashbacksViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cashback_rv,parent,false))
    }

    override fun onBindViewHolder(holder: CashbacksViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    inner class CashbacksViewHolder(val view: View): RecyclerView.ViewHolder(view){
        init {

        }
    }
    private class CashBacksDiffUtil: DiffUtil.ItemCallback<CashBacks>(){
        override fun areItemsTheSame(oldItem: CashBacks, newItem: CashBacks): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CashBacks, newItem: CashBacks): Boolean {
            return oldItem.isSelected == newItem.isSelected
        }

    }
}