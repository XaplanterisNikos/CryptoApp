package com.foxnks.cryptoapp.repository



import com.foxnks.cryptoapp.service.CryptoApiService

/**
 * Λειτουργεί ως "αποθετήριο" (repository),
    ένας σχεδιαστικός τρόπος για τη διαχείριση και παροχή δεδομένων στην εφαρμογή.
 *
 * Ιδιότητα apiService: Λαμβάνει ένα αντικείμενο τύπου CryptoApiService ως
 *  παράμετρο στον κατασκευαστή της, το οποίο περιέχει τις μεθόδους για την
 *  πρόσβαση στα δεδομένα μέσω του API.
 *
 * Μέθοδος getCryptoData(): Αυτή η συνάρτηση είναι suspend, που σημαίνει ότι μπορεί
 *  να καλεστεί σε coroutine και να ανασταλεί μέχρι να ολοκληρωθεί η εργασία της.
 *  Καλεί τη μέθοδο getCryptoData() της κλάσης CryptoApiService,
 *  η οποία αναμένεται να εκτελεί ένα αίτημα HTTP GET και να επιστρέφει δεδομένα
 *  κρυπτονομισμάτων.
 */
class CryptoRepository(private val apiService: CryptoApiService) {
    suspend fun getCryptoData() = apiService.getCryptoData()
}