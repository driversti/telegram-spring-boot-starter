package com.github.driversti.erepublik.telegram.keyboard;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KeyboardBuilder {

  private final List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
  private List<InlineKeyboardButton> row = null;

  public static KeyboardBuilder builder() {
    return new KeyboardBuilder();
  }

  public KeyboardBuilder row() {
    if (!isRowEnded()) {
      endRow();
      log.debug("The current row was not ended properly!");
    }
    this.row = new ArrayList<>();
    return this;
  }

  public KeyboardBuilder button(final String text, final String callbackData) {
    row.add(new InlineKeyboardButton(text).callbackData(callbackData));
    return this;
  }

  public KeyboardBuilder button(int value, final String callbackData) {
    return button(String.valueOf(value), callbackData);
  }

  public KeyboardBuilder endRow() {
    this.keyboard.add(this.row);
    this.row = null;
    return this;
  }

  public boolean isRowEnded() {
    return this.row == null;
  }

  public InlineKeyboardMarkup build() {
    if (!isRowEnded()) {
      endRow();
    }
    InlineKeyboardButton[][] inlineKeyboard = keyboard.stream()
        .map(list -> list.toArray(InlineKeyboardButton[]::new))
        .toArray(InlineKeyboardButton[][]::new);
    return new InlineKeyboardMarkup(inlineKeyboard);
  }
}
