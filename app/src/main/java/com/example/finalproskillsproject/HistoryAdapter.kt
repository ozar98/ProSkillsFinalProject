package com.example.finalproskillsproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text


class HistoryAdapter: ListAdapter<HistoryInfo, HistoryAdapter.HistoryViewHolder>(HistoryDiffUtil()){

    var onItemClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder=
        HistoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.history_rv,parent,false))




    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.name.text="Transaction from "+ getItem(position).fullName
        if (getItem(position).paymentMethod=="Visa"){
            holder.type.setImageResource(R.drawable.ic_credit_card)
        }else holder.type.setImageResource(R.drawable.ic_wallet)
        holder.amount.text=getItem(position).amount.toString()+" somoni"
    }






    private class HistoryDiffUtil: DiffUtil.ItemCallback<HistoryInfo>(){
        override fun areItemsTheSame(oldItem: HistoryInfo, newItem: HistoryInfo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HistoryInfo, newItem: HistoryInfo): Boolean {
            return oldItem.isSelected == newItem.isSelected
        }

    }
    inner class HistoryViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val name:TextView=view.findViewById(R.id.transaction_person)
        val date:TextView=view.findViewById(R.id.transaction_date)
        val amount:TextView=view.findViewById(R.id.transaction_amount)
        val type:ImageButton=view.findViewById(R.id.transaction_image)
        init {
            view.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition).id)
            }

        }
    }


}