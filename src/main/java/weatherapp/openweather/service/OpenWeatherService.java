package weatherapp.openweather.service;

import com.weather.api.OpenWeatherResponse;
import org.springframework.stereotype.Component;
import weatherapp.openweather.client.OpenWeatherClient;
import weatherapp.openweather.model.Current;
import weatherapp.openweather.model.WeatherResult;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class OpenWeatherService {

    final OpenWeatherClient client;

    @Inject
    public OpenWeatherService(final OpenWeatherClient client) {
        this.client = client;
    }

    public WeatherResult getTheWeather(final String lat, final String lon) {
        final OpenWeatherResponse weather = client.getWeather(lat, lon);
        return buildResult(weather);
    }

    public WeatherResult buildResult(final OpenWeatherResponse response) {
        final WeatherResult weatherResult = new WeatherResult();

        if (response != null) {
            weatherResult.setLat(response.getLat());
            weatherResult.setLon(response.getLon());
            weatherResult.setTimeZone(response.getTimezone());

            if (response.getCurrent() != null) {
                weatherResult.setCurrent(createCurrent(response.getCurrent()));
            }
        }
        return weatherResult;
    }

    private Current createCurrent(final com.weather.api.Current cur) {
        final Current current = new Current();
        current.setDt(convertToDateTime(cur.getDt()));
        current.setSunrise(convertToDateTime(Integer.parseInt(cur.getSunrise())));
        current.setClouds(cur.getClouds());
        current.setDewPoint(cur.getDewPoint());
        current.setFeelsLike(cur.getFeelsLike());
        return current;
    }

    private LocalDateTime convertToDateTime(final int epochTime) {
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(epochTime, 0, ZoneOffset.UTC);
//        System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(localDateTime));

        return localDateTime;
    }
}
