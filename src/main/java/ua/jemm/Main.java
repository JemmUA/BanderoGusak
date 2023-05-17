package ua.jemm;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.nio.charset.StandardCharsets;
import java.util.*;


public class Main extends TelegramLongPollingBot {

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(new Main());

    }

    @Override
    public String getBotUsername() {
        return "BanderaGusJemmBot";
    }

    @Override
    public String getBotToken() {
        return "6192699690:AAEwQqFcpQm0ZsRUPVccjO4O-JjnzKRon-U";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = getChatId(update);

        if (update.hasMessage() && update.getMessage().getText().equals("/start")) {
            SendMessage message = createMessage("Привіт!");
            message.setChatId(chatId);

/*
            attachButtons(message, Map.of(
                    "Слава Україні!", "glory_for_ukraine",
                    "Слава Нації!", "glory_to_nation"
            ));
            sendApiMethodAsync(message);
*/

            List<Map.Entry<String, String>> buttonsEntriesList = new ArrayList<>();
            buttonsEntriesList.add(new AbstractMap.SimpleEntry<>("Слава Україні!", "glory_for_ukraine"));
            buttonsEntriesList.add(new AbstractMap.SimpleEntry<>("Слава Нації!", "glory_to_nation"));
            buttonsEntriesList.add(new AbstractMap.SimpleEntry<>("Україна!", "ukraine"));
            attachButtons(message, buttonsEntriesList);
            sendApiMethodAsync(message);


        }

        if (update.hasCallbackQuery()) {
            if (update.getCallbackQuery().getData().equals("glory_for_ukraine")) {
                SendMessage message = createMessage("Героям Слава!");
                message.setChatId(chatId);
                sendApiMethodAsync(message);
            }

            if (update.getCallbackQuery().getData().equals("glory_to_nation")) {
                SendMessage message = createMessage("Смерть ворогам!");
                message.setChatId(chatId);
                sendApiMethodAsync(message);
            }

            if (update.getCallbackQuery().getData().equals("ukraine")) {
                SendMessage message = createMessage("Понад усе!");
                message.setChatId(chatId);
                sendApiMethodAsync(message);
            }

        }

    }

    public Long getChatId(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getFrom().getId();
        }
        if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getFrom().getId();
        }
        return null;
    }

    public SendMessage createMessage(String text) {
        SendMessage message = new SendMessage();
        message.setText(new String(text.getBytes(), StandardCharsets.UTF_8));
        message.setParseMode("markdown");
        return message;
    }

    public void attachButtons(SendMessage message, List<Map.Entry<String, String>> buttons) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        for (Map.Entry<String, String> buttonName : buttons) {
            String buttonValue = buttonName.getValue();
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(new String(buttonName.getKey().getBytes(), StandardCharsets.UTF_8));
            button.setCallbackData(buttonValue);
            keyboard.add(Arrays.asList(button));
        }


/*
    public void attachButtons(SendMessage message, Map<String, String> buttons) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        for (String buttonName : buttons.keySet()) {
            String buttonValue = buttons.get(buttonName);
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(new String(buttonName.getBytes(), StandardCharsets.UTF_8));
            button.setCallbackData(buttonValue);
            keyboard.add(Arrays.asList(button));
        }
*/
        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);
    }

}