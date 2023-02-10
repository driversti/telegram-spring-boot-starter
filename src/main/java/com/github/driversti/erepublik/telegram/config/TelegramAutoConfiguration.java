package com.github.driversti.erepublik.telegram.config;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(TelegramBot.class)
public class TelegramAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public TelegramBot telegramBot(@Value("${app.bot.token}") String botToken) {
    return new TelegramBot(botToken);
  }
}
