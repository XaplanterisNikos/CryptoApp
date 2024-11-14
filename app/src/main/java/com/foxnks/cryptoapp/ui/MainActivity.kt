package com.foxnks.cryptoapp.ui

import android.os.Bundle
import android.widget.Switch
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.foxnks.cryptoapp.R
import com.foxnks.cryptoapp.model.CryptoModel
import com.foxnks.cryptoapp.ui.adapter.CryptoAdapter
import com.foxnks.cryptoapp.viewmodel.CryptoViewModel
import com.foxnks.cryptoapp.viewmodel.CryptoViewModelFactory
import com.foxnks.cryptoapp.repository.CryptoRepository
import com.foxnks.cryptoapp.service.RetrofitInstance

class MainActivity : AppCompatActivity() {

    private val viewModel: CryptoViewModel by viewModels {
        CryptoViewModelFactory(CryptoRepository(RetrofitInstance.api))
    }

    private lateinit var statusMessage: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var currencySwitch: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI components
        statusMessage = findViewById(R.id.statusMessage)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        currencySwitch = findViewById(R.id.currencySwitch)

        // Initially load data in EUR
        viewModel.getCryptoDataEUR()
        viewModel.getHistoricalPrices()
        observeData()

        // Switch to toggle currency
        currencySwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                currencySwitch.text = "Switch to EUR"
                viewModel.getCryptoDataUSD()
            } else {
                currencySwitch.text = "Switch to USD"
                viewModel.getCryptoDataEUR()
            }
            // After currency change, refresh historical prices as well
            viewModel.getHistoricalPrices()
        }
    }

    // Observe the data and update the UI accordingly
    private fun observeData() {
        // Observe currency symbol change
        viewModel.currencySymbol.observe(this, Observer { symbol ->
            // Observe crypto data
            viewModel.cryptoData.observe(this, Observer { cryptoList ->
                // Observe historical prices
                viewModel.historicalPrices.observe(this, Observer { historicalPrices ->
                    if (cryptoList != null && historicalPrices != null) {
                        // Set status message when data is loaded successfully
                        statusMessage.text = "Connection successful."
                        // Set adapter with the updated data
                        recyclerView.adapter = CryptoAdapter(cryptoList, historicalPrices, symbol)
                    } else {
                        statusMessage.text = "Failed to load data."
                    }
                })
            })
        })
    }
}
