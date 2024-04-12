import com.google.gson.Gson;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Converter {
    private final Dotenv dotenv = Dotenv.load();
    private final String ApiKey = dotenv.get("API_KEY");
    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    public double convert(CurrencyCode from, CurrencyCode to, double value) {
        try {
            URI uri = new URI("https://v6.exchangerate-api.com/v6/" + ApiKey + "/pair/" + from + "/" + to);
            HttpRequest request = HttpRequest.newBuilder(uri).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            Conversion conversion = gson.fromJson(json, Conversion.class);
            return value * conversion.conversion_rate();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
