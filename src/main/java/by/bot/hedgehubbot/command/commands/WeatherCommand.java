package by.bot.hedgehubbot.command.commands;

import by.bot.hedgehubbot.command.core.Command;
import by.bot.hedgehubbot.command.core.TelegramCommand;
import by.bot.hedgehubbot.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Slf4j
@RequiredArgsConstructor
@TelegramCommand("/weather")
public class WeatherCommand implements Command {
    private final WeatherService weatherService;

    @Override
    public String getCommand() {
        return "/weather";
    }

    @Override
    public SendMessage execute(Message message) {
        log.info("Received /weather command");

        String text = message.getText();
        String[] parts = text.split(" ", 2);

        if (parts.length < 2) {
            return new SendMessage(
                    message.getChatId().toString(),
                    "Укажите город: /weather Minsk"
            );
        }

        String city = parts[1].trim();
        String weatherInfo = weatherService.getWeather(city);

        return new SendMessage(message.getChatId().toString(), weatherInfo);
    }
}
