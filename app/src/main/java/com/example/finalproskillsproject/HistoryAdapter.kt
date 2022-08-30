package com.example.finalproskillsproject

import android.annotation.SuppressLint
import android.provider.Settings.Secure.getString
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView



class HistoryAdapter: ListAdapter<TransactionResponse, HistoryAdapter.HistoryViewHolder>(HistoryDiffUtil()){

    var onItemClick: ((TransactionResponse) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder=
        HistoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.history_rv,parent,false))




    @SuppressLint("RestrictedApi", "SetTextI18n")
    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.name.text="Перевод от "+ getItem(position).sendername
        if (getItem(position).sendertype=="Card"){
            holder.type.setImageResource(R.drawable.ic_credit_card)
        }else holder.type.setImageResource(R.drawable.ic_wallet)
        holder.amount.text=getItem(position).amount.toString()+" somoni"
        holder.date.text=getItem(position).date
    }

    private class HistoryDiffUtil: DiffUtil.ItemCallback<TransactionResponse>(){
        override fun areItemsTheSame(oldItem: TransactionResponse, newItem: TransactionResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TransactionResponse, newItem: TransactionResponse): Boolean {
            return oldItem == newItem
        }

    }
    inner class HistoryViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val name:TextView=view.findViewById(R.id.transaction_person)
        val date:TextView=view.findViewById(R.id.transaction_date)
        val amount:TextView=view.findViewById(R.id.transaction_amount)
        val type:ImageButton=view.findViewById(R.id.transaction_image)
        init {
            view.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition))
            }

        }
    }


}