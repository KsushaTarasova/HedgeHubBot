package by.bot.hedgehubbot.command.commands;

import by.bot.hedgehubbot.command.core.Command;
import by.bot.hedgehubbot.command.core.TelegramCommand;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Slf4j
@TelegramCommand("/start")
public class StartCommand implements Command {
    @Override
    public String getCommand() {
        return "/start";
    }

    @Override
    public SendMessage execute(Message message) {
        log.info("Received /start command");

        Long chatId = message.getChatId();
        String firstName = message.getChat().getFirstName();

        return new SendMessage(chatId.toString(),
                String.format("Привет, %s! Я HedgeHubBot. Рад видеть тебя в числе ежиков!", firstName));

    }
}
