package com.example.finalproskillsproject

import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
//
//class HistoryAdapter:ListAdapter<HistoryInfo, HistoryAdapter.HistoryViewHolder> (HistoryDiffUtil()) {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder{
//        TODO()
//    }
//    override fun onBindViewHolder(holder: HistoryAdapter.HistoryViewHolder, position: Int) {
//        TODO()
//    }
//
//
//    private class HistoryDiffUtil:DiffUtil.ItemCallback<HistoryInfo>(){
//        override fun areItemsTheSame(oldItem: HistoryInfo, newItem: HistoryInfo): Boolean {
//            return oldItem.id==newItem.id
//        }
//
//        override fun areContentsTheSame(oldItem: HistoryInfo, newItem: HistoryInfo): Boolean {
//            return oldItem==newItem
//        }
//
//    }
//
//
//    inner class HistoryViewHolder(val view: View):RecyclerView.ViewHolder(view){
//
//    }
//
//}