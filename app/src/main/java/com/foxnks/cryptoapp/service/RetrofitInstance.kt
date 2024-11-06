package com.foxnks.cryptoapp.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Ορίζει ένα object που ονομάζεται RetrofitInstance, το οποίο χρησιμοποιείται για τη δημιουργία
 * και παροχή ενός μόνο (singleton) αντικειμένου CryptoApiService μέσω του Retrofit, με βάση μια
 * προκαθορισμένη διεύθυνση URL για το API.
 *
 * Με αυτό τον τρόπο, το RetrofitInstance.api παρέχει ένα έτοιμο προς χρήση instance του
 * CryptoApiService, επιτρέποντας στην εφαρμογή να καλεί τα endpoints του API του CoinGecko
 * για την ανάκτηση δεδομένων κρυπτονομισμάτων.
 */

/**
 * object RetrofitInstance: Η χρήση του object στην Kotlin δημιουργεί ένα singleton, που σημαίνει
 * ότι θα υπάρχει μόνο ένα instance αυτής της κλάσης καθ’ όλη τη διάρκεια ζωής της εφαρμογής.
 */
object RetrofitInstance {

    /**
     * private const val BASE_URL: Ορίζει τη βασική διεύθυνση URL για το API
     * (https://api.coingecko.com/api/v3/). Αυτή είναι η βάση όλων των αιτημάτων που θα
     * αποσταλούν μέσω του Retrofit.
     */
    private const val BASE_URL = "https://api.coingecko.com/api/v3/"

    /**
     * val api: CryptoApiService: Αυτή η ιδιότητα παρέχει ένα instance της διεπαφής
     * CryptoApiService μέσω του Retrofit. Χρησιμοποιεί τη λέξη-κλειδί by lazy, η οποία σημαίνει
     * ότι το instance θα δημιουργηθεί μόνο όταν χρειαστεί για πρώτη φορά.
     */
    val api: CryptoApiService by lazy {
        /**
         * Retrofit.Builder(): Δημιουργεί ένα νέο instance του Retrofit, το οποίο επιτρέπει να
         * ρυθμίσουμε την επικοινωνία με το API.
         *
         * baseUrl(BASE_URL): Ορίζει τη βασική διεύθυνση URL για το Retrofit, ώστε όλα τα αιτήματα
         * να χρησιμοποιούν αυτή τη διεύθυνση ως βάση.
         *
         * addConverterFactory(GsonConverterFactory.create()): Προσθέτει το GsonConverterFactory,
         * το οποίο επιτρέπει στο Retrofit να μετατρέπει αυτόματα τα JSON δεδομένα από το API σε
         * Kotlin αντικείμενα (CryptoModel), χρησιμοποιώντας τη βιβλιοθήκη Gson.
         *
         * build(): Κατασκευάζει το Retrofit instance με την καθορισμένη διαμόρφωση.
         *
         * create(CryptoApiService::class.java): Δημιουργεί ένα instance του CryptoApiService και
         * συνδέει τις μεθόδους της διεπαφής CryptoApiService με το
         * Retrofit για την εκτέλεση αιτημάτων.
         */
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoApiService::class.java)
    }
}