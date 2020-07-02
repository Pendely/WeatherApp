package weatherapp.openweather.client;

import com.weather.api.OpenWeatherResponse;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriTemplateHandler;
import weatherapp.openweather.config.OpenWeatherConfig;

import javax.inject.Inject;
import java.net.URI;

import static org.springframework.http.HttpMethod.GET;

@Service
public class OpenWeatherClient {

    private final String testLat = "59.334591";
    private final String testLon = "18.0632409";
    private final String exclude = "daily";

    private final RestTemplate restTemplate;
    private final String basePath;

    @Inject
    public OpenWeatherClient(final RestTemplate restTemplate, final OpenWeatherConfig config) {

        this.restTemplate = restTemplate;
        this.basePath = config.getHost() + config.getCodePath();
    }

    public OpenWeatherResponse getWeather(final String lat, final String lon) {

        final URI uri = new DefaultUriTemplateHandler().expand(basePath, lat, lon, exclude);
        final RequestEntity request = new RequestEntity(GET, uri);

        try {
            final ResponseEntity<OpenWeatherResponse> response = restTemplate.exchange(request, OpenWeatherResponse.class);

//            final ResponseEntity<String> testResponse = restTemplate.exchange(request, String.class);
//            System.out.println(testResponse);

            return response.getBody();

        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
