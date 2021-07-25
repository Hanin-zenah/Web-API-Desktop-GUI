package SCD2_2021_Exam.model;

import SCD2_2021_Exam.model.inputapi.TheOneInput;
import SCD2_2021_Exam.model.outputapi.PastebinOutput;

public interface ModelFacade {
    /**
     * Sets the input API to be accessed later.<br><br>
     *
     * @param mode The input API.
     */
    void setInputApi(TheOneInput mode);

    /**
     * A simple getter for the assigned input API.<br><br>
     *
     * @return The input API.
     */
    TheOneInput getInputApi();

    /**
     * Sets the output API to be accessed later.<br><br>
     *
     * @param mode The output API.
     */
    void setOutputApi(PastebinOutput mode);

    /**
     * A simple getter for the assigned output API.<br><br>
     *
     * @return The output API.
     */
    PastebinOutput getOutputApi();

    /**
     * Lists the available characters in the-one input API.<br><br>
     *
     * @return The available characters.
     */
    String listAvailableCharacters();

    /**
     * Lists the available quotes of a given character from the input API.<br><br>
     *
     * @param characterID The character ID.
     * @return The available quotes for this character.
     */
    String listAvailableQuotes(String characterID);

    /**
     * Gets the information on a selected character from the input API. <br><br>
     *
     * @param characterID The character ID to get information on.
     * @return The available information on this character.
     */
    String getCharacterInfo(String characterID);

    /**
     * Sends a report with the given data to the pastebin output API.<br><br>
     *
     * @param data The data to send the report on.
     * @return The link to the created pastebin paste.
     */
    String sendReport(String data);

    /**
     * Inserts a new row with the given ID and quote to the database
     * If a row with the given ID already exists, this will replace that row in the table.<br><br>
     *
     * @param characterId The character ID to insert.
     * @param quote The character's quote to cache.
     */
    void insertIntoDb(String characterId, String quote);

    /**
     * Given a character's ID, fetches the character's cached available quotes
     * from the database. <br><br>
     *
     * @param characterId The character ID to fetch the matching row for.
     * @return the cached quote. null if no row with the given ID exists in the database
     */
    String getCachedQuote(String characterId);

    /**
     * Counts the number of available quotes in a given string.<br><br>
     *
     * @param quotes The character's available quotes.
     * @return the number of quotes quote.
     */
    int countQuotes(String quotes);

    /**
     * Sets the threshold result count if it has not been previously set.<p></p>
     * This will be the threshold for which a screen will blink and the sent report will start with * if the selected character's
     * number of available quotes is larger than the threshold.
     *
     * @param value The value of the threshold
     */
    void setThreshold(int value);

    int getThreshold();

    int getLowerBound();

    int getUpperBound();
}
