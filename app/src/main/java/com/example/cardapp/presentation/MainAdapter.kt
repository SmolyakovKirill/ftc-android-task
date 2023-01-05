package com.example.cardapp.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cardapp.R
import com.example.cardapp.data.db.RequestCard


class MainAdapter(listMain:ArrayList<RequestCard>) : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {
    var listArray = listMain

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvBin:TextView = itemView.findViewById(R.id.tvBinValue)
        val tvBank:TextView = itemView.findViewById(R.id.tvRcBankValue)

        fun setData(title:RequestCard){
            tvBin.text = title.bin
            tvBank.text = title.bank
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyViewHolder(inflater.inflate(R.layout.rc_item, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setData(listArray[position])
    }

    override fun getItemCount(): Int {
        return listArray.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(listItems:List<RequestCard>){
        listArray.clear()
        listArray.addAll(listItems)
        notifyDataSetChanged()
    }

}