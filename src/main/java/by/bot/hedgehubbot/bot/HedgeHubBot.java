package by.bot.hedgehubbot.bot;

import by.bot.hedgehubbot.command.core.CommandDispatcher;
import by.bot.hedgehubbot.config.BotConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
@RequiredArgsConstructor
public class HedgeHubBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;
    private final CommandDispatcher commandDispatcher;

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }


    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();

        if (message != null && message.hasText()) {
            try {
                execute(commandDispatcher.dispatch(message));
            } catch (TelegramApiException e) {
                log.error("Exception occurred while executing command {}", message.getText(), e);
            }

        }
    }
}
