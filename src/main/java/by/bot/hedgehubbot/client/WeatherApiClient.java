package by.bot.hedgehubbot.client;

import by.bot.hedgehubbot.models.WeatherResponse;
import feign.FeignException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "weather-client",
        url = "${weather.api.url}"
)
public interface WeatherApiClient {

    @GetMapping
    @Retryable(retryFor = FeignException.class,
            backoff = @Backoff(delay = 1000, multiplier = 2))
    WeatherResponse getWeather(
            @RequestParam("q") String city,
            @RequestParam("appid") String apiKey,
            @RequestParam("units") String units,
            @RequestParam("lang") String lang
    );
}
