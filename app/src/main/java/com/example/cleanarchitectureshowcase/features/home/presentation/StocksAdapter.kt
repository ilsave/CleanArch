package com.example.cleanarchitectureshowcase.features.home.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitectureshowcase.R
import com.example.cleanarchitectureshowcase.features.home.data.StocksDTO

class StocksAdapter: RecyclerView.Adapter<StocksAdapter.StocksHolder>() {

    private var stocks: List<StocksDTO> = listOf()

    class StocksHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val ticket: TextView = itemView.findViewById(R.id.tv_ticket)
        val companyName: TextView = itemView.findViewById(R.id.tv_company_name)
        val price: TextView = itemView.findViewById(R.id.tv_current_price)
        val dayChangePercent: TextView = itemView.findViewById(R.id.tv_day_delta)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StocksHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_stocks_element, parent, false)
        return StocksHolder(view)
    }

    override fun onBindViewHolder(holder: StocksHolder, position: Int) {
        val item = stocks[position]

        // this abomination...
        with(holder) {
            ticket.text = item.symbol
            companyName.text = item.name
            price.text = "$".plus(item.price)
            if (item.changesPercentage >= 0) {
                dayChangePercent.text = "+$"
                    .plus(item.change)
                    .plus(" (")
                    .plus(String.format("%.2f", item.changesPercentage))
                    .plus("%)")
            } else {

                dayChangePercent.text = "-$"
                    .plus(kotlin.math.abs(item.change))
                    .plus(" (")
                    .plus(String.format("%.2f", item.changesPercentage))
                    .plus("%)")
            }
        }

        val defaultColor = holder.itemView.background
        if (position % 2 != 0) {
            defaultColor.setTint(ContextCompat.getColor(holder.itemView.context, R.color.white))
        } else {
            defaultColor.setTint(ContextCompat.getColor(holder.itemView.context, R.color.bg_grey))
        }
        holder.itemView.background = defaultColor

        if (item.changesPercentage < 0) {
            holder.dayChangePercent.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.red))
        } else {
            holder.dayChangePercent.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.green))
        }

    }

    override fun getItemCount(): Int {
        return stocks.size
    }

    fun setItems(newItems: List<StocksDTO>) {
        stocks = newItems
        notifyDataSetChanged()
    }
}