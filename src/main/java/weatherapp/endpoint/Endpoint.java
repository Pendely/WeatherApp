package weatherapp.endpoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import weatherapp.openweather.model.WeatherResult;
import weatherapp.openweather.service.OpenWeatherService;

import javax.inject.Inject;

@Service
@RestController
public class Endpoint {

    private final OpenWeatherService service;

    @Inject
    public Endpoint(final OpenWeatherService service) {
        this.service = service;
    }

    @GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WeatherResult> getWeatherBasedOnGeoLocation(
            @RequestParam(value = "lat", required = false) final String lat,
            @RequestParam(value = "lon", required = false) final String lon) {

        final WeatherResult theWeather = service.getTheWeather(lat, lon);
        return new ResponseEntity<>(theWeather, HttpStatus.OK);
    }
}
