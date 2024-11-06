package com.foxnks.cryptoapp

import android.os.Bundle
import android.widget.TextView

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.foxnks.cryptoapp.service.RetrofitInstance
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.foxnks.cryptoapp.adapter.CryptoAdapter
import com.foxnks.cryptoapp.repository.CryptoRepository
import com.foxnks.cryptoapp.viewmodel.CryptoViewModel
import com.foxnks.cryptoapp.viewmodel.CryptoViewModelFactory

class MainActivity : AppCompatActivity() {

    private val viewModel: CryptoViewModel by viewModels {
        CryptoViewModelFactory(CryptoRepository(RetrofitInstance.api))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val statusMessage: TextView = findViewById(R.id.statusMessage)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.cryptoData.observe(this, Observer { cryptoList ->
            if (cryptoList != null) {
                statusMessage.text = "Connection successful: 200 OK"
                recyclerView.adapter = CryptoAdapter(cryptoList)
            } else {
                statusMessage.text = "Failed to load data."
            }
        })
    }
}