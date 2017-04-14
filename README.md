Za ovu zadaću korištene su dvije aktivnosti: ListActivity za prikazivanje liste zadataka i NewTask za dodavanje novog zadatka. U Task klasi nalaze se konstruktori za objekt u listi. Funkcionalnost je izvedana pomoću RecyclerView-a i pripadajućeg adaptera koji se nalazi u klasi NoteAdapter. Za baze korištena je NoteDbHelper. I još je dodana jedna klasa koja isrcatava liniju između elemenata u RecyclerView-u. Imala sam malo problema oko brisanja objekta iz baze jer nisam baš znala kako to implementirati u adapter, ali sam na kraju riješila problem. A za drugi problem oko toolbar-a našla sam rješenje na Stacku, tj. bilo je potrebno kombinirati više ponuđenih rješenja.

Literatura:
  1. http://stackoverflow.com/questions/41042024/delete-data-from-database-inside-recycleview
  2. http://stackoverflow.com/questions/34979957/android-status-bar-is-white
  3. http://stackoverflow.com/questions/24618829/how-to-add-dividers-and-spaces-between-items-in-recyclerview
  4. Materijali s laboratorijskih vježbi
