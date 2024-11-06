package com.foxnks.cryptoapp.service

import com.foxnks.cryptoapp.model.CryptoModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Διεπαφή CryptoApiService: Αυτή η διεπαφή ορίζει μια μέθοδο που χρησιμοποιείται
 * για την αλληλεπίδραση με το API των κρυπτονομισμάτων.
 * Το Retrofit μπορεί να την υλοποιήσει και να εκτελέσει HTTP αιτήματα, βασιζόμενο
 * στη δήλωση της μεθόδου εδώ.
 *
 * Ανατολή @GET("coins/markets"): Ορίζει ότι η μέθοδος getCryptoData θα εκτελέσει
 * ένα HTTP GET αίτημα στο endpoint coins/markets.
 *
 * Μέθοδος getCryptoData: Είναι μια suspend μέθοδος, η οποία σημαίνει ότι
 * μπορεί να κληθεί σε μια coroutine και να ανασταλεί μέχρι να λάβει απάντηση.
 *
 * Με αυτή τη διεπαφή, το Retrofit μπορεί να εκτελέσει αιτήματα για την ανάκτηση δεδομένων
 * κρυπτονομισμάτων, όπως τιμές και άλλες πληροφορίες για συγκεκριμένα νομίσματα σε συγκεκριμένο
 * νόμισμα.
 */
interface CryptoApiService {
    /**
     * vs_currency: Ορίζει τη νομισματική μονάδα για τα δεδομένα των κρυπτονομισμάτων.
     * Στην προεπιλεγμένη τιμή έχει οριστεί "eur".
     *
     * ids: Περιέχει τα συγκεκριμένα ids των κρυπτονομισμάτων που θα επιστραφούν.
     * Η προεπιλεγμένη τιμή περιλαμβάνει το Bitcoin και το Ethereum
     * ("bitcoin,ethereum").
     *
     * order: Καθορίζει τη σειρά ταξινόμησης των δεδομένων με προεπιλεγμένη τιμή
     * "market_cap_desc", η οποία τα ταξινομεί κατά κεφαλαιοποίηση αγοράς, φθίνουσα.
     */
    @GET("coins/markets")
    suspend fun getCryptoData(
        @Query("vs_currency") currency: String = "eur",
        @Query("ids") ids: String = "bitcoin,ethereum",
        @Query("order") order: String = "market_cap_desc"
    ): Response<List<CryptoModel>>
}