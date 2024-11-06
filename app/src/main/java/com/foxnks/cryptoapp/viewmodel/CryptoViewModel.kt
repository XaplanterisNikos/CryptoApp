package com.foxnks.cryptoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

import com.foxnks.cryptoapp.repository.CryptoRepository
import kotlinx.coroutines.Dispatchers

/**
 *  Κλάση CryptoViewModel: Αυτή η κλάση είναι τύπου ViewModel, που σημαίνει ότι
 *  διατηρείται στη μνήμη για όσο διάστημα το UI χρειάζεται τα δεδομένα.
 *  Έτσι, διαχειρίζεται δεδομένα που επιβιώνουν σε αλλαγές του UI, όπως η περιστροφή
 *  της οθόνης.
 *
 * Ιδιότητα repository: Λαμβάνει ένα αντικείμενο CryptoRepository μέσω του κατασκευαστή,
 * το οποίο περιέχει τη λογική για την ανάκτηση των δεδομένων από το API.
 *
 * Ιδιότητα cryptoData: Είναι μια LiveData που περιέχει τα δεδομένα των
 * κρυπτονομισμάτων. Χρησιμοποιεί την εντολή liveData και εκτελείται στον Dispatchers.IO,
 * έναν dispatcher που προορίζεται για ενέργειες εισόδου-εξόδου, ώστε η κλήση στο API
 * να γίνεται εκτός του κύριου νήματος.
 *
 * Με αυτόν τον τρόπο, το CryptoViewModel προσφέρει στο UI τα δεδομένα κρυπτονομισμάτων
 * μέσω της cryptoData και το UI μπορεί να παρακολουθεί αυτή την LiveData για να
 * ενημερώνεται αυτόματα όταν αλλάξουν τα δεδομένα.
 */
class CryptoViewModel(private val repository: CryptoRepository) : ViewModel() {

    /**
     * Εκτελεί ένα αίτημα στο repository.getCryptoData() για την ανάκτηση των
     * δεδομένων κρυπτονομισμάτων.
     *
     * Έλεγχος της απόκρισης: Αν το response.isSuccessful είναι αληθές,
     * δηλαδή η απάντηση από το API ήταν επιτυχής (status code 200),
     * καλεί τη μέθοδο emit(response.body()) για να ενημερώσει το LiveData
     * με τα δεδομένα που επέστρεψε το API.
     *
     * Αν η απόκριση είναι αποτυχημένη, καλεί emit(null) για να ενημερώσει ότι δεν
     * υπάρχουν δεδομένα, π.χ., λόγω κάποιου σφάλματος κατά τη λήψη.
     */
    val cryptoData = liveData(Dispatchers.IO) {
        val response = repository.getCryptoData()
        if (response.isSuccessful) {
            emit(response.body())
        } else {
            emit(null)
        }
    }
}