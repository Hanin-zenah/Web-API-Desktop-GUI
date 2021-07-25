package SCD2_2021_Exam.model.inputapi;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TheOneInputOnline implements TheOneInput {
    /** The access token to the-one API. */
    private String token = null;

    /**
     * Sets the access token to the API.<br><br>
     *
     * @param token The access token. Must not be null. Must not be empty or blank.
     * @throws IllegalArgumentException if any argument requirements are breached.
     */
    @Override
    public void setToken(String token) throws IllegalArgumentException {
        if(token == null || token.isEmpty() || token.isBlank()) {
            throw new IllegalArgumentException("Provided token is null or invalid");
        }
        this.token = token;
    }

    /**
     * Modifies a string to look more readable.<br><br>
     *
     * @param stringBuilder The StringBuilder containing the string to beautify.
     * @return The beautified string.
     */
    private String stringBeautify(StringBuilder stringBuilder) {
        return stringBuilder.toString().replaceAll("\\[]", "Nothing to display]")
                                       .replaceAll("},\\{", "\n\n")
                                       .replaceAll("\",\"", "\n")
                                       .replaceAll("\"", "")
                                       .replaceAll(":", ": ")
                                       .replaceAll("\\{", "")
                                       .replaceAll("}", "")
                                       .replaceAll("_id", "ID")
                                       .replaceAll("],", "\n\n")
                                       .replaceAll("docs: \\[", "");
    }

    /**
     * Lists the available characters in the API.<br><br>
     *
     * <b>Preconditions:</b><br>
     * access token must be set<br>
     *
     * @return The list of available characters if token is set, otherwise null.
     * @throws IllegalStateException if token is null.
     */
    @Override
    public String listAvailableCharacters() throws IllegalStateException {
        if(this.token == null) {
            throw new IllegalStateException("Cannot perform request: token is null");
        }
        String link = "https://the-one-api.dev/v2/character";
        StringBuilder builder = connectAndExtract(link);
        if(builder != null) {
            return stringBeautify(builder);
        }
        return null;
    }

    /**
     * Lists the available quotes of a given character from the API.<br><br>
     *
     * <b>Preconditions:</b><br>
     * access token must be set<br>
     *
     * @param characterID The character ID.
     * @return The available quotes for this character if token is set, otherwise null.
     * @throws IllegalStateException if token is null.
     */
    @Override
    public String listAvailableQuotes(String characterID) throws IllegalStateException {
        if(this.token == null) {
            throw new IllegalStateException("Cannot perform request: token is null");
        }
        String link = "https://the-one-api.dev/v2/character/" + characterID + "/quote";
        StringBuilder builder = connectAndExtract(link);
        if(builder != null) {
            return stringBeautify(builder);
        }
        return null;
    }

    /**
     * Gets the information on a selected character.<br><br>
     *
     * <b>Preconditions:</b><br>
     * access token must be set<br>
     *
     * @param characterID The character ID to get information on.
     * @return The available information on this character if token is set, otherwise null.
     * @throws IllegalStateException if token is null.
     */
    @Override
    public String getCharacterInfo(String characterID) throws IllegalStateException {
        if(this.token == null) {
            throw new IllegalStateException("Cannot perform request: token is null");
        }
        String link = "https://the-one-api.dev/v2/character/" + characterID;
        StringBuilder builder = connectAndExtract(link);
        if(builder != null) {
            return stringBeautify(builder);
        }
        return null;
    }

    private StringBuilder connectAndExtract(String link) {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(link);
            request.setHeader("User-Agent", "Java client");
            request.setHeader("Authorization", "Bearer " + this.token);
            request.setHeader("Content-Type", "application/x-www-form-urlencoded");

            CloseableHttpResponse response = client.execute(request);

            BufferedReader bufReader = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = bufReader.readLine()) != null) {
                builder.append(line);
                builder.append(System.lineSeparator());
            }
            return builder;
        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
        return null;
    }
}