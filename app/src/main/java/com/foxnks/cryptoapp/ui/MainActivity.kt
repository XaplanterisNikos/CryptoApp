package com.foxnks.cryptoapp.ui

import android.os.Bundle
import android.widget.TextView

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.foxnks.cryptoapp.service.RetrofitInstance
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.foxnks.cryptoapp.R
import com.foxnks.cryptoapp.ui.adapter.CryptoAdapter
import com.foxnks.cryptoapp.repository.CryptoRepository
import com.foxnks.cryptoapp.viewmodel.CryptoViewModel
import com.foxnks.cryptoapp.viewmodel.CryptoViewModelFactory

class MainActivity : AppCompatActivity() {

    /**
     * private val viewModel: CryptoViewModel: Δημιουργεί ένα instance του CryptoViewModel
     * χρησιμοποιώντας τον CryptoViewModelFactory. Ο CryptoViewModelFactory δημιουργεί το
     * CryptoViewModel παρέχοντας το CryptoRepository (που με τη σειρά του έχει πρόσβαση στο
     * API μέσω του RetrofitInstance.api).
     */
    private val viewModel: CryptoViewModel by viewModels {
        CryptoViewModelFactory(CryptoRepository(RetrofitInstance.api))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * statusMessage: Ένα TextView που χρησιμοποιείται για να εμφανίζει το αποτέλεσμα
         * της σύνδεσης (αν ήταν επιτυχής ή όχι).
         */
        val statusMessage: TextView = findViewById(R.id.statusMessage)

        /**
         * recyclerView: Ένα RecyclerView που εμφανίζει τα δεδομένα των κρυπτονομισμάτων.
         * Ο LinearLayoutManager ορίζει ότι τα στοιχεία θα εμφανίζονται κατακόρυφα.
         */
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        /**
         * viewModel.cryptoData.observe(...): Το observe παρακολουθεί τις αλλαγές στην LiveData
         * cryptoData. Όταν τα δεδομένα ενημερώνονται:
         * Αν cryptoList δεν είναι null, σημαίνει ότι τα δεδομένα έχουν φορτωθεί επιτυχώς:
         * Η statusMessage εμφανίζει το μήνυμα επιτυχίας "Connection successful: 200 OK".
         * Το recyclerView ενημερώνει τον προσαρμογέα (CryptoAdapter) με τη
         * λίστα δεδομένων cryptoList.
         * Αν cryptoList είναι null, σημαίνει ότι υπήρξε πρόβλημα κατά τη φόρτωση,
         * και η statusMessage δείχνει το μήνυμα "Failed to load data."
         */
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