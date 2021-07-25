package SCD2_2021_Exam.model.outputapi;

public class PastebinOutputOffline implements PastebinOutput {
    /**
     * Sets the access token to the API.<br><br>
     *
     * @param token The access token.
     */
    @Override
    public void setToken(String token) {}

    /**
     * Sends a report with the given data to the API.<br><br>
     *
     * @param data The data to send the report on.
     * @return A dummy link.
     */
    @Override
    public String sendReport(String data) {
        return "https://pastebin.com/dummyURL";
    }
}
