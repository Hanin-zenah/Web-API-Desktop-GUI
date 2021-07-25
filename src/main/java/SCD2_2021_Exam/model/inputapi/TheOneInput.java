package SCD2_2021_Exam.model.inputapi;

import java.util.Scanner;

public interface TheOneInput {
    /**
     * Sets the access token to the API.<br><br>
     *
     * @param token The access token.
     */
    void setToken(String token);

    /**
     * Lists the available characters in the API <br><br>
     *
     * @return The available characters.
     */
    String listAvailableCharacters();

    /**
     * Lists the available quotes of a given character from the API <br><br>
     *
     * @param characterID The character ID.
     * @return The available quotes for this character.
     */
    String listAvailableQuotes(String characterID);

    /**
     * Gets the information on a selected character <br><br>
     *
     * @param characterID The character ID to get information on.
     * @return The available information on this character.
     */
    String getCharacterInfo(String characterID);

    /**
     * Counts the number of available quotes in a given string.<br><br>
     *
     * @param quotes The character's available quotes.
     * @return the number of quotes quote. 0 if the quote is null or empty, or if the string is incorrectly formatted.
     */
    default int countQuotes(String quotes) {
        if(quotes == null || quotes.isEmpty()) {
            return 0;
        }
        String keyWord = "total: ";
        int index = quotes.lastIndexOf(keyWord);
        int count = 0;
        if(index != -1) {
            //get the next int
            String sub = quotes.substring(index + keyWord.length()).replaceAll(",", " ");
            Scanner scan = new Scanner(sub);
            if(scan.hasNextInt()) {
                count = scan.nextInt();
            }
            else {
                System.out.println("There is no next int, the output formatting might have changed in the API");
            }
            scan.close();
        }
        return count;
    }
}
