package SCD2_2021_Exam.model.database;

import java.sql.*;

public class DatabaseCache implements Database {
    /** The database URL to connect to. */
    private final String databaseUrl = "jdbc:sqlite:characters.db";

    /**
     * Constructor. Connects to the specified database and creates a new characters table
     * with two columns, ID and quote, upon first run of the program. <br><br>
     */
    public DatabaseCache() {
        Connection connection = connectToDb();
        try {
            // create a database connection
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS characters (id STRING PRIMARY KEY, quotes STRING)"
            );
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        closeConnection(connection);
    }

    /**
     * Creates a new Connection object that connects to the database.<br><br>
     *
     * @return The connection object if successful. null otherwise.
     */
    private Connection connectToDb() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(databaseUrl);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return connection;
    }

    /**
     * Inserts a new row with the given ID and quote to the database.
     * If a row with the given ID already exists, this will replace that row in the table.<br><br>
     *
     * @param id The character ID to insert.
     * @param quote The character's quote to cache.
     */
    public void insertCharacter(String id, String quote) {
        Connection connection = connectToDb();
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(15);

            //replace all single quotes with two single quotes to avoid syntax error and
            //store the quote correctly
            quote = quote.replaceAll("'", "''");

            //replace into will insert or replace an existing entry in the table
            statement.executeUpdate("REPLACE INTO characters (id, quotes)" +
                    "VALUES ('" + id + "', '" + quote + "')");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        closeConnection(connection);
    }

    /**
     * Given a character's ID, fetches the character's cached available quotes
     * from the database. <br><br>
     *
     * @param id The character ID to fetch matching row.
     * @return The cached quote. null if no row with the given ID exists in the database
     */
    public String fetchCharacterQuotes(String id) {
        Connection connection = connectToDb();
        String result = null;
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(15);
            //replace into will insert or replace an existing entry in the table
            ResultSet rs = statement.executeQuery("SELECT * FROM characters" +
                    " WHERE id = '" + id + "'");
            if(rs.next()) {
                //row exists
                result = rs.getString("quotes");
            }
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
        closeConnection(connection);
        return result;
    }

    /**
     * Given a Connection object, closes that connection.<br><br>
     *
     * @param connection The connection to close.
     */
    private void closeConnection(Connection connection) {
        try {
            if(connection != null) {
                connection.close();
            }
        } catch(SQLException e) {
            // connection close failed.
            System.err.println(e.getMessage());
        }
    }
}