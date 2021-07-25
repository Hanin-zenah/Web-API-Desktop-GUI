package SCD2_2021_Exam.model.database;

public interface Database {
    /**
     * Inserts a new row with the given ID and quote to the database.
     * If a row with the given ID already exists, this will replace that row in the table.<br><br>
     *
     * @param id The character ID to insert.
     * @param quote The character's quote to cache.
     */
    void insertCharacter(String id, String quote);

    /**
     * Given a character's ID, fetches the character's cached available quotes
     * from the database. <br><br>
     *
     * @param id The character ID to fetch matching row.
     * @return The cached quote. null if no row with the given ID exists in the database.
     */
    String fetchCharacterQuotes(String id);
}
