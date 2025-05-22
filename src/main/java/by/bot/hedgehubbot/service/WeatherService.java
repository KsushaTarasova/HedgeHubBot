package by.bot.hedgehubbot.service;

import by.bot.hedgehubbot.models.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String apiUrl;

    public String getWeather(String city) {
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("q", city)
                .queryParam("appid", apiKey)
                .queryParam("units", "metric")
                .queryParam("lang", "ru")
                .toUriString();

        try {
            WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);
            return formatWeather(response, city);
        } catch (Exception e) {
            return "Не удалось получить погоду для города " + city;
        }
    }

    private String formatWeather(WeatherResponse response, String city) {
        if (response == null || response.getWeather() == null) {
            return "Данные о погоде отсутствуют";
        }

        WeatherResponse.Weather weather = response.getWeather()[0];

        return String.format(
                "🌤 Погода в %s:\n" +
                        "🌡 Температура: %.1f°C (ощущается как %.1f°C)\n" +
                        "📊 Влажность: %d%%\n" +
                        "🌬 Ветер: %.1f м/с\n" +
                        "☁️ Состояние: %s\n",
                city,
                response.getMain().getTemp(),
                response.getMain().getFeelsLike(),
                response.getMain().getHumidity(),
                response.getWind().getSpeed(),
                weather.getDescription()
        );
    }
}
