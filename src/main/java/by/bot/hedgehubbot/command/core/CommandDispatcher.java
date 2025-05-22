package by.bot.hedgehubbot.command.core;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommandDispatcher {
    private final ApplicationContext context;
    private final Map<String, Command> commands = new HashMap<>();

    @PostConstruct
    public void init() {
        Map<String, Object> commandBeans = context.getBeansWithAnnotation(TelegramCommand.class);
        for (Object bean : commandBeans.values()) {
            if (bean instanceof Command cmd) {
                String commandName = cmd.getCommand();
                commands.put(cmd.getCommand(), cmd);
                log.info("Registered command: {}", commandName);
            }
        }
    }

    public SendMessage dispatch(Message message) {
        String commandText = message.getText().split(" ")[0];
        Command command = commands.get(commandText);

        return command != null
                ? command.execute(message)
                : new SendMessage(message.getChatId().toString(), "❌ Unknown command: " + commandText);
    }
}
