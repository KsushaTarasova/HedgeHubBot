package by.bot.hedgehubbot.service;

import by.bot.hedgehubbot.client.WeatherApiClient;
import by.bot.hedgehubbot.models.WeatherResponse;
import by.bot.hedgehubbot.utils.Templates;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherService {
    private final WeatherApiClient weatherApiClient;

    @Value("${weather.api.key}")
    private String apiKey;

    public String getWeather(String city) {
        try {
            WeatherResponse response = weatherApiClient.getWeather(
                    city,
                    apiKey,
                    "metric",
                    "ru"
            );
            return formatWeather(response, city);
        } catch (Exception e) {
            log.info("Failed to get weather for {}", city);
            return "Не удалось получить погоду для " + city;
        }
    }

    private String formatWeather(WeatherResponse response, String city) {
        if (response == null || response.getWeather() == null) {
            log.info("Weather data not found for {}", city);
            return "Данные о погоде отсутствуют";
        }

        WeatherResponse.Weather weather = response.getWeather()[0];

        return String.format(
                Templates.WEATHER_TEMPLATE.getTemplate(),
                city,
                response.getMain().getTemp(),
                response.getMain().getFeelsLike(),
                response.getMain().getHumidity(),
                response.getWind().getSpeed(),
                weather.getDescription()
        );
    }
}
