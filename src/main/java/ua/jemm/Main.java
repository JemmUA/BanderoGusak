package ua.jemm;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;


public class Main extends TelegramLongPollingBot {
    private Map<Long, Integer> levels = new HashMap<>();
    private int banderoGusakNumber = 71;


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
        String currentLevelButton;


        if ((update.hasMessage() && update.getMessage().getText().equals("/start")) ||
                (update.hasCallbackQuery() && (update.getCallbackQuery().getData().equals("final") && getLevel(chatId) == 5))) {
            currentLevelButton = "level-1_task";
            // set level-1
            setLevel(chatId, 1);
            // send image level-1
            sendImage("level-1", chatId);

            // send message
            SendMessage message = createMessage("Ґа-ґа-ґа!\n" +
                    "Вітаємо у боті біолабораторії «Батько наш Бандера».\n" +
                    "\n" +
                    "Ти отримуєш гусака №" + banderoGusakNumber + "\n" +
                    "\n" +
                    "Цей бот ми створили для того, щоб твій гусак прокачався з рівня звичайної свійської худоби до рівня біологічної зброї, здатної нищити ворога. \n" +
                    "\n" +
                    "Щоб звичайний гусак перетворився на бандерогусака, тобі необхідно:\n" +
                    "✔\uFE0Fвиконувати завдання\n" +
                    "✔\uFE0Fпереходити на наступні рівні\n" +
                    "✔\uFE0Fзаробити достатню кількість монет, щоб придбати Джавеліну і зробити свєрхтра-та-та.\n" +
                    "\n" +
                    "*Гусак звичайний.* Стартовий рівень.\n" +
                    "Бонус: 5 монет.\n" +
                    "Обери завдання, щоб перейти на наступний рівень\n");
            message.setChatId(chatId);

            List<String> buttons = Arrays.asList(
                    "Сплести маскувальну сітку (+15 монет)",
                    "Зібрати кошти патріотичними піснями (+15 монет)",
                    "Вступити в Міністерство Мемів України (+15 монет)",
                    "Запустити волонтерську акцію (+15 монет)",
                    "Вступити до лав тероборони (+15 монет))");
            buttons = getRandom(buttons, 3);
            Map<String, String> buttonsComplete = new HashMap<>();
            for (String buttonName : buttons) {
                buttonsComplete.put(buttonName, currentLevelButton);
            }

            attachButtons(message, buttonsComplete);
            sendApiMethodAsync(message);

        }

        if (update.hasCallbackQuery()) {
            if (update.getCallbackQuery().getData().equals("level-1_task") && getLevel(chatId) == 1) {
                currentLevelButton = "level-2_task";

                // set level-2
                setLevel(chatId, 2);
                // send image level-2
                sendImage("level-2", chatId);

                // send message
                SendMessage message = createMessage("" +
                        "*Вітаємо на другому рівні! Твій гусак - біогусак.*\n" +
                        "Баланс: 20 монет. \n" +
                        "Обери завдання, щоб перейти на наступний рівень\n");
                message.setChatId(chatId);

                List<String> buttons = Arrays.asList(
                        "Зібрати комарів для нової біологічної зброї (+15 монет)",
                        "Пройти курс молодого бійця (+15 монет)",
                        "Задонатити на ЗСУ (+15 монет)",
                        "Збити дрона банкою огірків (+15 монет)",
                        "Зробити запаси коктейлів Молотова (+15 монет)"
                );
                buttons = getRandom(buttons, 3);
                Map<String, String> buttonsComplete = new HashMap<>();
                for (String buttonName : buttons) {
                    buttonsComplete.put(buttonName, currentLevelButton);
                }
                attachButtons(message, buttonsComplete);
                sendApiMethodAsync(message);
            }

            if (update.getCallbackQuery().getData().equals("level-2_task") && getLevel(chatId) == 2) {
                currentLevelButton = "level-3_task";
                // set level-3
                setLevel(chatId, 3);
                // send image level-3
                sendImage("level-3", chatId);

                // send message
                SendMessage message = createMessage("" +
                        "*Вітаємо на третьому рівні! Твій гусак - бандеростажер.*\n" +
                        "Баланс: 35 монет. \n" +
                        "Обери завдання, щоб перейти на наступний рівень\n");
                message.setChatId(chatId);

                List<String> buttons = Arrays.asList(
                        "Злітати на тестовий рейд по чотирьох позиціях (+15 монет)",
                        "Відвезти гуманітарку на передок (+15 монет)",
                        "Знайти зрадника та здати в СБУ (+15 монет)",
                        "Навести арту на орків (+15 монет)",
                        "Притягнути танк трактором (+15 монет)"
                );
                buttons = getRandom(buttons, 3);
                Map<String, String> buttonsComplete = new HashMap<>();
                for (String buttonName : buttons) {
                    buttonsComplete.put(buttonName, currentLevelButton);
                }

                attachButtons(message, buttonsComplete);
                sendApiMethodAsync(message);
            }

            if (update.getCallbackQuery().getData().equals("level-3_task") && getLevel(chatId) == 3) {
                currentLevelButton = "level-4_task";

                // set level-4
                setLevel(chatId, 4);
                // send image level-4
                sendImage("level-4", chatId);

                // send message
                SendMessage message = createMessage("" +
                        "*Вітаємо на останньому рівні! Твій гусак - готова біологічна зброя - бандерогусак.*\n" +
                        "Баланс: 50 монет. \n" +
                        "Тепер ти можеш придбати Джавелін і глушити чмонь\n");
                message.setChatId(chatId);

                List<String> buttons = Arrays.asList(
                        "Купити Джавелін (50 монет)"
                );
                Map<String, String> buttonsComplete = new HashMap<>();
                for (String buttonName : buttons) {
                    buttonsComplete.put(buttonName, currentLevelButton);
                }
                attachButtons(message, buttonsComplete);
                sendApiMethodAsync(message);
            }

            if (update.getCallbackQuery().getData().equals("level-4_task") && getLevel(chatId) == 4) {
                currentLevelButton = "final";

                // set level-5
                setLevel(chatId, 5);
                // send image level-5
                sendImage("final", chatId);

                // send message
                SendMessage message = createMessage("*Джавелін твій. Повний вперед!*");
                message.setChatId(chatId);
                attachButtons(message, Map.of("Оце таааак!!! Хочу ще одного Бандерогусака!", currentLevelButton, "I'll be back!", "exit"));
                sendApiMethodAsync(message);

                banderoGusakNumber++;
            }

            if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("exit")) {
                SendMessage message = createMessage("*Я полетів! До зустрічі, друже!!! Ґа-ґа-ґа!*");
                message.setChatId(chatId);
                sendApiMethodAsync(message);
                System.exit(1);
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
        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);
    }

    public void sendImage(String name, Long chatId) {
        SendAnimation animation = new SendAnimation();

        InputFile inputFile = new InputFile();
        inputFile.setMedia(new File("images/" + name + ".gif"));

        animation.setAnimation(inputFile);
        animation.setChatId(chatId);

        executeAsync(animation);

    }

    public int getLevel(Long chatId) {
        return levels.getOrDefault(chatId, 1);
    }

    public void setLevel(Long chatId, int level) {
        levels.put(chatId, level);
    }

    public List<String> getRandom(List<String> variants, int numberOfVariants) {
        Collections.shuffle(variants);
        return variants.stream()
                .limit(numberOfVariants)
                .collect(Collectors.toList());
    }
}