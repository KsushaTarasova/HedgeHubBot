package by.bot.hedgehubbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@EnableFeignClients
@SpringBootApplication
public class HedgeHubBotApplication {
    public static void main(String[] args) {
        SpringApplication.run(HedgeHubBotApplication.class, args);
    }
}
