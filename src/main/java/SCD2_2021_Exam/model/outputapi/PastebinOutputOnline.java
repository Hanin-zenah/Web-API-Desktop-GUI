package SCD2_2021_Exam.model.outputapi;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PastebinOutputOnline implements PastebinOutput {
    /** The access token to the pastebin API. */
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
     * Sends a report with the given data to the API.<br><br>
     *
     * <b>Preconditions:</b><br>
     * access token must be set<br>
     *
     * @param data The data to send the report on.
     * @return The link to the created pastebin paste. Or null if token is null.
     * @throws IllegalStateException if token is null.
     */
    @Override
    public String sendReport(String data) throws IllegalStateException {
        if(token == null) {
            throw new IllegalStateException("Cannot perform request: token is null");
        }
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            //This url is to create a new paste
            String link = "https://pastebin.com/api/api_post.php";
            HttpPost request = new HttpPost(link);
            request.setHeader("User-Agent", "Java client");
            request.setHeader("Content-Type", "application/x-www-form-urlencoded");

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("api_dev_key", this.token));
            params.add(new BasicNameValuePair("api_paste_code", data));
            params.add(new BasicNameValuePair("api_option", "paste"));
            request.setEntity(new UrlEncodedFormEntity(params));

            CloseableHttpResponse response = client.execute(request);

            var bufReader = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            var builder = new StringBuilder();

            String line;

            while ((line = bufReader.readLine()) != null) {
                builder.append(line);
                builder.append(System.lineSeparator());
            }
            return builder.toString();
        } catch (Exception e) {
            System.out.println("Exception in NetClientPost:- " + e);
        }
        return null;
    }
}
