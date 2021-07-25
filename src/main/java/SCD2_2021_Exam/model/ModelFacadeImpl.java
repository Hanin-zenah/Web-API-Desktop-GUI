package SCD2_2021_Exam.model;

import SCD2_2021_Exam.model.database.Database;
import SCD2_2021_Exam.model.inputapi.TheOneInputOffline;
import SCD2_2021_Exam.model.inputapi.TheOneInput;
import SCD2_2021_Exam.model.inputapi.TheOneInputOnline;
import SCD2_2021_Exam.model.outputapi.PastebinOutput;
import SCD2_2021_Exam.model.outputapi.PastebinOutputOffline;
import SCD2_2021_Exam.model.outputapi.PastebinOutputOnline;

public class ModelFacadeImpl implements ModelFacade {
    /** The tokens stored in the configuration file. */
    private final ReadConfig tokens;

    /** The input API. */
    private TheOneInput theOneInput;

    /** The output API. */
    private PastebinOutput pastebinOutput;

    /** The database used for caching. */
    private final Database db;

    /** The result count threshold specified by the user. */
    private int threshold;

    /** The upper bound of the threshold value */
    private final int upperBound = 50;

    /** The lower bound of the threshold value */
    private final int lowerBound = 3;

    /**
     * Constructor. Fetches the access tokens from the configuration file, sets the input and output
     * APIs accordingly, and sets the database as well.<br><br>
     *
     * @param inputMode The mode in which the input API would be created. Must not be null. Must be either "online" or "offline".
     * @param outputMode The mode in which the output API would be created. Must not be null. Must be either "online" or "offline".
     * @param db The database that will be used for caching. Must not be null.
     * @throws IllegalArgumentException if any of the arguments requirements are breached.
     */
    public ModelFacadeImpl(String inputMode, String outputMode, Database db) throws IllegalArgumentException {
        tokens = new ReadConfig();

        if(inputMode == null|| outputMode == null || db == null) {
            throw new IllegalArgumentException("Arguments must not be null");
        }

        if(inputMode.equals("online")) {
            setInputApi(new TheOneInputOnline());
        } else if(inputMode.equals("offline")) {
            setInputApi(new TheOneInputOffline());
        } else {
            throw new IllegalArgumentException("Argument is invalid, must be \"online\" or \"offline\"");
        }

        if(outputMode.equals("online")) {
            setOutputApi(new PastebinOutputOnline());
        } else if(outputMode.equals("offline")) {
            setOutputApi(new PastebinOutputOffline());
        } else {
            throw new IllegalArgumentException("Argument is invalid, must be \"online\" or \"offline\"");
        }

        this.db = db;
        this.threshold = -1;
    }

    /**
     * Sets the input API to be accessed later.<br><br>
     *
     * @param mode The input API.
     */
    @Override
    public void setInputApi(TheOneInput mode) {
        this.theOneInput = mode;
        if(mode != null) {
            this.theOneInput.setToken(tokens.getInputApiToken());
        }

    }

    /**
     * A simple getter for the assigned input API.<br><br>
     *
     * @return The input API.
     */
    @Override
    public TheOneInput getInputApi() {
        return this.theOneInput;
    }

    /**
     * A simple getter for the assigned output API.<br><br>
     *
     * @return The output API.
     */
    @Override
    public PastebinOutput getOutputApi() {
        return this.pastebinOutput;
    }

    /**
     * Sets the output API to be accessed later.<br><br>
     *
     * @param mode The output API.
     */
    @Override
    public void setOutputApi(PastebinOutput mode) {
        this.pastebinOutput = mode;
        if(mode != null) {
            this.pastebinOutput.setToken(tokens.getOutputApiToken());
        }
    }

    /**
     * Lists the available characters in the input API <br><br>
     *
     * <b>Preconditions:</b><br>
     * the-one input API must be set<br>
     *
     * @return The list of available characters if token is set, otherwise null.
     * @throws IllegalStateException if the input API is not set.
     */
    @Override
    public String listAvailableCharacters() throws IllegalStateException {
        if(this.theOneInput == null) {
            throw new IllegalStateException("Input API is not set");
        }
        return this.theOneInput.listAvailableCharacters();
    }

    /**
     * Lists the available quotes of a given character from the-one input API.<br><br>
     *
     * <b>Preconditions:</b><br>
     * the-one input API must be set<br>
     *
     * @param characterID The character ID. Must not be null.
     * @return The available quotes for this character.
     * @throws IllegalStateException if the input API is not set.
     * @throws IllegalArgumentException if argument requirement is breached.
     */
    @Override
    public String listAvailableQuotes(String characterID) throws IllegalArgumentException, IllegalStateException {
        if(this.theOneInput == null) {
            throw new IllegalStateException("Input API is not set");
        }
        if (characterID == null) {
            throw new IllegalArgumentException("Character ID can not be null");
        }
        return this.theOneInput.listAvailableQuotes(characterID);
    }

    /**
     * Gets the information on a selected character from the-one input API. <br><br>
     *
     * <b>Preconditions:</b><br>
     * the-one input API must be set<br>
     *
     * @param characterID The character ID to get information on. Must not be null.
     * @return The available information on this character.
     * @throws IllegalStateException If the input API is not set.
     * @throws IllegalArgumentException If argument requirements are breached.
     */
    @Override
    public String getCharacterInfo(String characterID) throws IllegalArgumentException, IllegalStateException {
        if(this.theOneInput == null) {
            throw new IllegalStateException("Input API is not set");
        }
        if(characterID == null) {
            throw new IllegalArgumentException("Character ID can not be null");
        }
        return this.theOneInput.getCharacterInfo(characterID);
    }

    /**
     * Sends a report with the given data to the pastebin output API.<br><br>
     *
     * <b>Preconditions:</b><br>
     * pastebin output API must be set<br>
     *
     * @param data The data to send the report on.
     * @return The link to the created pastebin paste. null if data is null.
     * @throws IllegalStateException If output API is null.
     */
    @Override
    public String sendReport(String data) {
        if(this.pastebinOutput == null) {
            throw new IllegalStateException("Output API is not set");
        }
        if(data != null) {
            return this.pastebinOutput.sendReport(data);
        }
        return null;
    }

    /**
     * Inserts a new row with the given ID and quote to the database.
     * If a row with the given ID already exists, this will replace that row in the table.<br><br>
     *
     * <b>Preconditions:</b><br>
     * database must be set<br>
     *
     * @param characterId The character ID to insert. Must not be null, empty or blank.
     * @param quote The character's quote to cache. Must not be null.
     * @throws IllegalStateException if database is not set.
     * @throws IllegalArgumentException if any of the arguments requirements are breached.
     */
    @Override
    public void insertIntoDb(String characterId, String quote) throws IllegalArgumentException, IllegalStateException {
        if(this.db == null) {
            throw new IllegalStateException("Database is not set");
        }
        //check if null or empty
        if(characterId == null || quote == null) {
            throw new IllegalArgumentException("Parameters must not be null");
        }
        if(characterId.isBlank() || characterId.isEmpty()) {
            throw new IllegalArgumentException("Character id cannot be empty");
        }
        db.insertCharacter(characterId, quote);
    }

    /**
     * Given a character's ID, fetches the character's cached available quotes
     * from the database. <br><br>
     *
     * <b>Preconditions:</b><br>
     * database must be set<br>
     *
     * @param characterId The character ID to fetch the matching row for. Must not be null.
     * @return the cached quote. null if no row with the given ID exists in the database.
     * @throws IllegalStateException if database is not set.
     * @throws IllegalArgumentException if characterId is null.
     */
    @Override
    public String getCachedQuote(String characterId) throws IllegalArgumentException, IllegalStateException {
        if(this.db == null) {
            throw new IllegalStateException("Database is not set");
        }
        if(characterId == null) {
            throw new IllegalArgumentException("Character ID can not be null");
        }
        return db.fetchCharacterQuotes(characterId);
    }

    /**
     * Counts the number of available quotes in a given string.<br><br>
     *
     * @param quotes The character's available quotes.
     * @return the number of quotes quote.
     */
    @Override
    public int countQuotes(String quotes) {
        return theOneInput.countQuotes(quotes);
    }

    /**
     * Sets the threshold result count if it has not been previously set.<p></p>
     * This will be the threshold for which a screen will blink and the sent report will start with * if the selected character's
     * number of available quotes is larger than the threshold.
     *
     * @param value The value of the threshold
     */
    @Override
    public void setThreshold(int value) {
        //only set if threshold has not been set already
        if(this.threshold == -1) {
            if(value >= lowerBound && value <= upperBound) {
                this.threshold = value;
            }
        }
    }

    @Override
    public int getThreshold() {
        return threshold;
    }

    @Override
    public int getLowerBound() {
        return lowerBound;
    }

    @Override
    public int getUpperBound() {
        return upperBound;
    }
}
