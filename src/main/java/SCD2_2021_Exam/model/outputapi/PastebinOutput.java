package SCD2_2021_Exam.model.outputapi;

public interface PastebinOutput {
    /**
     * Sets the access token to the API.<br><br>
     *
     * @param token The access token.
     */
    void setToken(String token);

    /**
     * Sends a report with the given data to the API.<br><br>
     *
     * @param data The data to send the report on.
     * @return The link to the created pastebin paste.
     */
    String sendReport(String data);
}
