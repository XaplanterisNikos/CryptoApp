        com/
        └── example/
            └── cryptoinfo/
                ├── api/
                │   └── CryptoApiService.kt
                │   └── RetrofitInstance.kt
                ├── model/
                │   └── CryptoModel.kt
                ├── repository/
                │   └── CryptoRepository.kt
                ├── ui/
                │   ├── adapter/
                │   │   └── CryptoAdapter.kt
                │   └── MainActivity.kt
                └── viewmodel/
                    └── CryptoViewModel.kt
                    └── CryptoViewModelFactory.kt

api/ (CryptoApiService και RetrofitInstance)

    CryptoApiService: Αυτό είναι το interface που ορίζει το endpoint του API. 
    Περιέχει τη μέθοδο getCryptoData(), η οποία κάνει την κλήση στο API για να λάβει δεδομένα 
    για τα κρυπτονομίσματα.
    
    RetrofitInstance: Περιέχει μια Retrofit instance, η οποία είναι υπεύθυνη για την δημιουργία του 
    αντικειμένου CryptoApiService. Παρέχει πρόσβαση στο API με την κατάλληλη βάση URL 
    και διαμορφώσεις για JSON parsing.
    
    Συνεργασία: Το RetrofitInstance αρχικοποιεί το CryptoApiService, επιτρέποντας να 
    χρησιμοποιηθεί το getCryptoData() για την ανάκτηση δεδομένων από το CoinGecko API.


model/ (CryptoModel)

    CryptoModel: Είναι το data class που αντιπροσωπεύει τα δεδομένα που λαμβάνονται από το API. 
    Περιλαμβάνει ιδιότητες όπως id, symbol, και current_price, οι οποίες αντιστοιχούν στα πεδία 
    JSON από την απάντηση του API.
    
    Συνεργασία: Χρησιμοποιείται από το CryptoRepository και το ViewModel για να αποθηκεύει 
    και να μεταφέρει δεδομένα κρυπτονομισμάτων.


repository/ (CryptoRepository)

    CryptoRepository: Αυτή η κλάση είναι υπεύθυνη για την αλληλεπίδραση με το API μέσω του 
    CryptoApiService. Περιέχει τη μέθοδο fetchCryptoData(), η οποία καλεί το getCryptoData() 
    από το CryptoApiService και επιστρέφει τη λίστα των κρυπτονομισμάτων.
    
    Συνεργασία: Το CryptoRepository χρησιμοποιείται από το CryptoViewModel για να ζητάει δεδομένα 
    από το API. Έτσι, ο κώδικας που σχετίζεται με τη λήψη δεδομένων από το API διατηρείται 
    συγκεντρωμένος στο Repository.

viewmodel/ (CryptoViewModel και CryptoViewModelFactory)

    CryptoViewModel: Η κλάση ViewModel που διατηρεί τα δεδομένα που χρειάζεται η διεπαφή χρήστη 
    (UI). Καλεί το CryptoRepository για να ανακτήσει τα δεδομένα των κρυπτονομισμάτων μέσω της 
    fetchCryptoData(). Παρέχει ένα LiveData αντικείμενο (cryptoData) το οποίο παρατηρείται από το 
    UI ώστε να ενημερωθεί για νέες τιμές κρυπτονομισμάτων.
    
    CryptoViewModelFactory: Είναι ένα Factory class που βοηθάει στη δημιουργία του CryptoViewModel 
    και επιτρέπει τη διέλευση εξωτερικών παραμέτρων (όπως του CryptoRepository) στο CryptoViewModel.
    
    Συνεργασία: Το CryptoViewModel συνδέει το CryptoRepository με την MainActivity. Είναι υπεύθυνο 
    για τη διαχείριση της λογικής των δεδομένων και την προώθηση των πληροφοριών στην διεπαφή χρήστη.

ui/ (MainActivity και adapter/CryptoAdapter)

    MainActivity: Είναι η κύρια δραστηριότητα της εφαρμογής. Παρατηρεί το LiveData από το 
    CryptoViewModel και ενημερώνει το UI (RecyclerView) όταν υπάρχουν νέες πληροφορίες για 
    τα κρυπτονομίσματα. Επίσης, εμφανίζει το μήνυμα σύνδεσης 200 OK όταν η κλήση είναι επιτυχής.
    
    CryptoAdapter: Το adapter για το RecyclerView που εμφανίζει τα δεδομένα των κρυπτονομισμάτων 
    στην MainActivity. Χρησιμοποιεί τα δεδομένα από το CryptoModel για να δημιουργήσει τις 
    καταχωρήσεις της λίστας.
    
    Συνεργασία: Η MainActivity λαμβάνει τα δεδομένα από το CryptoViewModel και τα περνάει στο 
    CryptoAdapter για να τα εμφανίσει στο UI.

Ροή Κλήσεων και Συνεργασία Κλάσεων
MainActivity ξεκινά και δημιουργεί μια instance του CryptoViewModel, το 
οποίο διαχειρίζεται την παρουσίαση των δεδομένων.

CryptoViewModel χρησιμοποιεί το CryptoRepository για να κάνει fetch τα δεδομένα 
των κρυπτονομισμάτων από το API.

CryptoRepository καλεί το CryptoApiService μέσω του RetrofitInstance για να πραγματοποιήσει 
την κλήση GET στο CoinGecko API.

CryptoApiService λαμβάνει τα δεδομένα των κρυπτονομισμάτων και τα επιστρέφει ως λίστα 
από CryptoModel αντικείμενα στο CryptoRepository.

Το CryptoRepository επιστρέφει τα δεδομένα στο CryptoViewModel, το οποίο τα 
αποθηκεύει σε ένα LiveData αντικείμενο.

Η MainActivity παρατηρεί το LiveData και, όταν ενημερώνονται τα δεδομένα, τα περνάει 
στο CryptoAdapter για να εμφανιστούν στο UI.
