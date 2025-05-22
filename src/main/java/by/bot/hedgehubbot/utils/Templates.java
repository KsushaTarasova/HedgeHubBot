package by.bot.hedgehubbot.utils;

import lombok.Getter;

@Getter
public enum Templates {
    WEATHER_TEMPLATE(
            """
                    🌤 Погода в %s:
                    🌡 Температура: %.1f°C (ощущается как %.1f°C)
                    📊 Влажность: %d%%
                    🌬 Ветер: %.1f м/с
                    ☁️ Состояние: %s
                    """
    );

    final String template;

    Templates(String template) {
        this.template = template;
    }
}
