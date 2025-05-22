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
        log.info("Received /start command from chat");

        Long chatId = message.getChatId();
        String username = message.getChat().getUserName();

        return new SendMessage(chatId.toString(),
                String.format("Hello, %s! I'm HedgeHubBot.", username));

    }
}
