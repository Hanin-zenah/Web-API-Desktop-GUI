package SCD2_2021_Exam.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ReadConfig {
    /** The JSONObject to get the stored tokens. */
    private JSONObject configuration;

    /** The path to the stored configuration json file. */
    private final String file = "/config.json";

    /**
     * Constructor. Creates the JSONObject to get the specified configuration from the configuration file.<br><br>
     */
    public ReadConfig() {
        try {
            URI uri = new URI(this.getClass().getResource(file).toString());
            JSONParser jp = new JSONParser();
            configuration = (JSONObject) jp.parse(new FileReader(new File(uri.getPath())));
        } catch (URISyntaxException | IOException | ParseException | NullPointerException e) {
            System.err.println("Invalid file.");
            System.exit(1);
        }
    }

    /**
     * Gets the specified access token for the-one API.<br><br>
     *
     * @return The token.
     */
    public String getInputApiToken() {
        return (String) configuration.get("inputApiToken");
    }

    /**
     * Gets the specified access token for pastebin API.<br><br>
     *
     * @return The token.
     */
    public String getOutputApiToken() {
        return (String) configuration.get("outputApiToken");
    }
}
