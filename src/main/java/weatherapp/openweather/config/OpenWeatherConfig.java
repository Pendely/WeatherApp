package weatherapp.openweather.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "openWeather")
public class OpenWeatherConfig {

    private String host;
    private String codePath;

    public String getHost()
    {
        return host;
    }

    public String getCodePath()
    {
        return codePath;
    }

    public void setHost(final String host)
    {
        this.host = host;
    }

    public void setCodePath(final String codePath)
    {
        this.codePath = codePath;
    }
}
