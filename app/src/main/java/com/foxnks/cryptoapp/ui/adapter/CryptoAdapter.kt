package com.foxnks.cryptoapp.ui.adapter



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.foxnks.cryptoapp.R

import com.foxnks.cryptoapp.model.CryptoModel

class CryptoAdapter(private val cryptoList: List<CryptoModel>) :
    RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {

    class CryptoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idTextView: TextView = itemView.findViewById(R.id.cryptoId)
        val symbolTextView: TextView = itemView.findViewById(R.id.cryptoSymbol)
        val priceTextView: TextView = itemView.findViewById(R.id.cryptoPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_crypto, parent, false)
        return CryptoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        val crypto = cryptoList[position]
        holder.idTextView.text = crypto.id
        holder.symbolTextView.text = crypto.symbol
        holder.priceTextView.text = "€${crypto.current_price}"
    }

    override fun getItemCount() = cryptoList.size
}
