package by.bot.hedgehubbot.command.core;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface Command {
    String getCommand();
    SendMessage execute(Message message);
}
