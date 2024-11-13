package com.foxnks.cryptoapp.ui.adapter



import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.foxnks.cryptoapp.R

import com.foxnks.cryptoapp.model.CryptoModel
import org.w3c.dom.Text

class CryptoAdapter(
    private val cryptoList: List<CryptoModel>,
    private val currencySymbol: String
) : RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {

    class CryptoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idTextView: TextView = itemView.findViewById(R.id.cryptoId)
        val symbolTextView: TextView = itemView.findViewById(R.id.cryptoSymbol)
        val priceTextView: TextView = itemView.findViewById(R.id.cryptoPrice)
        //        val high24hTextView: TextView = itemView.findViewById(R.id.highPrice)
//        val low24hTextView: TextView = itemView.findViewById(R.id.lowPrice)
//        val marketCapTextView: TextView = itemView.findViewById(R.id.marketCap)
        val image: ImageView = itemView.findViewById(R.id.logoImg)
        //        val priceChange24hTextView: TextView = itemView.findViewById(R.id.priceChange24)
        val priceChangePercentage24hTextView: TextView = itemView.findViewById(R.id.priceChangePercentage24)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cryptoo, parent, false)
        return CryptoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        val crypto = cryptoList[position]
        holder.idTextView.text = "Name: "+ crypto.id
        holder.symbolTextView.text = "Symbol: "+ crypto.symbol
        holder.priceTextView.text = "Current Price:  $currencySymbol${crypto.current_price}"
//        holder.marketCapTextView.text = "Market Cap: $currencySymbol${crypto.market_cap}"
//        holder.high24hTextView.text = "High 24: $currencySymbol${crypto.high_24h}"
//        holder.low24hTextView.text = "Low 24: $currencySymbol${crypto.low_24h}"
        Glide.with(holder.itemView.context)
            .load(crypto.image)  // Load the image URL
            .into(holder.image)   // Set it to the ImageView in the ViewHolder
//        holder.priceChange24hTextView.text = "Price Change 24h: $currencySymbol${crypto.price_change_24h}"
        holder.priceChangePercentage24hTextView.text = "Price Change  24h: ${crypto.price_change_percentage_24h}%"
        holder.priceChangePercentage24hTextView.setTextColor(
            if (crypto.price_change_percentage_24h > 0) Color.GREEN else Color.RED
        )
    }

    override fun getItemCount() = cryptoList.size
}