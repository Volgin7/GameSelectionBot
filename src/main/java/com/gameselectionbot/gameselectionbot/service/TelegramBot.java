package com.gameselectionbot.gameselectionbot.service;

import com.gameselectionbot.gameselectionbot.config.BotConfig;
import jakarta.validation.constraints.Null;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    final BotConfig config;
    static int answerIndex = 0;
    static long chatIdGlobal =0;
    static String[] answers = {"","","","",""};

    public TelegramBot(BotConfig config) {
        this.config = config;
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            String messageText = update.getMessage().getText();
            switch (messageText) {
                case "/start":
                    // System.out.println("answers = " + Arrays.toString(answers));
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    answerIndex = 0;
                    answerQuestionOne(chatId);
                    // System.out.println("chatId = " + chatId);
                    chatIdGlobal = chatId;
                    // System.out.println("chatIdGlobal = " + chatIdGlobal);
                    // System.out.println("answers = " + Arrays.toString(answers));
                    // selectGames(chatId, answers);
                    break;
                default:
                    sendMessage(chatId,"Для начала введите команду /start");
            }
        } else if (update.hasCallbackQuery()) {
            switch (answerIndex) {
                case 0:
                    answers[0] = update.getCallbackQuery().getData();
                    // System.out.println("answers = " + Arrays.toString(answers) + " Index = " + answerIndex);
                    answerIndex = 1;
                    answerQuestionTwo(chatIdGlobal);
                    break;
                case 1:
                    answers[1] = update.getCallbackQuery().getData();
                    // System.out.println("answers = " + Arrays.toString(answers) + " Index = " + answerIndex);
                    answerIndex = 2;
                    answerQuestionThree(chatIdGlobal);
                    break;
                case 2:
                    answers[2] = update.getCallbackQuery().getData();
                    // System.out.println("answers = " + Arrays.toString(answers) + " Index = " + answerIndex);
                    answerIndex = 3;
                    answerQuestionFour(chatIdGlobal);
                    break;
                case 3:
                    answers[3] = update.getCallbackQuery().getData();
                    // System.out.println("answers = " + Arrays.toString(answers) + " Index = " + answerIndex);
                    answerIndex = 4;
                    answerQuestionFive(chatIdGlobal);
                    break;
                case 4:
                    answers[4] = update.getCallbackQuery().getData();
                    // System.out.println("answers = " + Arrays.toString(answers) + " Index = " + answerIndex);
                    answerIndex = 5;
                    gameSelection(chatIdGlobal, answers);
                    break;
            }
        }


    } // end onUpdateReceived

    public void gameSelection (long chatId, String[] answers) {
        ComputerGames[] game = new ComputerGames[28];

        game[0] = new ComputerGames(
                "Battlefield",
                "ИГРОВОЙ","ИГРОВОЙ","ИГРОВОЙ",
                "более 80ГБ", "более 80ГБ", "более 80ГБ",
                "ЭКШЕН", "ЭКШЕН", "ЭКШЕН",
                "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ","МНОГОПОЛЬЗОВАТЕЛЬСКУЮ","ОДНОПОЛЬЗОВАТЕЛЬСКУЮ",
                "ВАЖНО","ВАЖНО","ВАЖНО"
        );

        game[1] = new ComputerGames(
                "Valheim",
                "НЕИГРОВОЙ", "НЕИГРОВОЙ", "НЕИГРОВОЙ",
                "до 5ГБ", "до 5ГБ", "до 5ГБ",
                "ПРИКЛЮЧЕНИЯ", "ПРИКЛЮЧЕНИЯ", "ПРИКЛЮЧЕНИЯ",
                "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ","ОДНОПОЛЬЗОВАТЕЛЬСКУЮ",
                "НЕ ВАЖНО", "НЕ ВАЖНО", "НЕ ВАЖНО"
        );

        game[2] = new ComputerGames(
                "Satisfactory",
                "ИГРОВОЙ", "ИГРОВОЙ", "ИГРОВОЙ",
                "5-20ГБ", "5-20ГБ", "5-20ГБ",
                "ПРИКЛЮЧЕНИЯ", "ПРИКЛЮЧЕНИЯ", "ПРИКЛЮЧЕНИЯ",
                "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ",
                "ВАЖНО", "ВАЖНО", "ВАЖНО"
        );

        game[3] = new ComputerGames(
                "Warhammer 4000",
                "ИГРОВОЙ", "ИГРОВОЙ", "ИГРОВОЙ",
                "20-60ГБ", "20-60ГБ", "20-60ГБ",
                "ЭКШЕН", "ЭКШЕН", "ЭКШЕН",
                "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ",
                "ВАЖНО", "ВАЖНО", "ВАЖНО"
        );

        game[4] = new ComputerGames(
                "Atomic heart",
                "ИГРОВОЙ", "ИГРОВОЙ", "ИГРОВОЙ",
                "60-80ГБ", "60-80ГБ", "60-80ГБ",
                "ЭКШЕН", "ЭКШЕН", "ЭКШЕН",
                "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ",
                "ВАЖНО", "ВАЖНО", "ВАЖНО"
        );

        game[5] = new ComputerGames(
                "GTA 5",
                "ИГРОВОЙ", "ИГРОВОЙ", "ИГРОВОЙ",
                "более 80ГБ", "более 80ГБ", "более 80ГБ",
                "ЭКШЕН", "ЭКШЕН", "ЭКШЕН",
                "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "МНОГОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ",
                "ВАЖНО", "ВАЖНО", "ВАЖНО"
        );

        game[6] = new ComputerGames(
                "Dota 2",
                "НЕИГРОВОЙ", "НЕИГРОВОЙ", "НЕИГРОВОЙ",
                "20-60ГБ", "20-60ГБ", "20-60ГБ",
                "СТРАТЕГИЯ", "СТРАТЕГИЯ", "СТРАТЕГИЯ",
                "ОМНОГОПОЛЬЗОВАТЕЛЬСКУЮ", "МНОГОПОЛЬЗОВАТЕЛЬСКУЮ", "МНОГОПОЛЬЗОВАТЕЛЬСКУЮ",
                "НЕ ВАЖНО", "НЕ ВАЖНО", "НЕ ВАЖНО"
        );

        game[7] = new ComputerGames(
                "CS GO",
                "НЕИГРОВОЙ", "НЕИГРОВОЙ", "НЕИГРОВОЙ",
                "5-20ГБ", "5-20ГБ", "5-20ГБ",
                "ЭКШЕН", "ЭКШЕН", "ЭКШЕН",
                "ОМНОГОПОЛЬЗОВАТЕЛЬСКУЮ", "МНОГОПОЛЬЗОВАТЕЛЬСКУЮ", "МНОГОПОЛЬЗОВАТЕЛЬСКУЮ",
                "НЕ ВАЖНО", "НЕ ВАЖНО", "НЕ ВАЖНО"
        );

        game[8] = new ComputerGames(
                "RDR 2",
                "ИГРОВОЙ", "ИГРОВОЙ", "ИГРОВОЙ",
                "более 80ГБ", "более 80ГБ", "более 80ГБ",
                "ПРИКЛЮЧЕНИЯ", "ПРИКЛЮЧЕНИЯ", "ПРИКЛЮЧЕНИЯ",
                "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ",
                "ВАЖНО", "ВАЖНО", "ВАЖНО"
        );

        game[9] = new ComputerGames(
                "Fallout 4,3,76",
                "ИГРОВОЙ", "ИГРОВОЙ", "ИГРОВОЙ",
                "5-20ГБ", "5-20ГБ", "5-20ГБ",
                "ПРИКЛЮЧЕНИЯ", "ПРИКЛЮЧЕНИЯ", "ПРИКЛЮЧЕНИЯ",
                "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "МНОГОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ",
                "ВАЖНО", "ВАЖНО", "ВАЖНО"
        );

        game[10] = new ComputerGames(
                "Dead by daylight",
                "ИГРОВОЙ", "ИГРОВОЙ", "ИГРОВОЙ",
                "5-20ГБ", "5-20ГБ", "5-20ГБ",
                "УЖАСЫ", "УЖАСЫ", "УЖАСЫ",
                "МНОГОПОЛЬЗОВАТЕЛЬСКУЮ", "МНОГОПОЛЬЗОВАТЕЛЬСКУЮ", "МНОГОПОЛЬЗОВАТЕЛЬСКУЮ",
                "ВАЖНО", "ВАЖНО", "ВАЖНО"
        );

        game[11] = new ComputerGames(
                "Cyberpunk",
                "ИГРОВОЙ", "ИГРОВОЙ", "ИГРОВОЙ",
                "60-80ГБ", "60-80ГБ", "60-80ГБ",
                "ПРИКЛЮЧЕНИЯ", "ПРИКЛЮЧЕНИЯ", "ПРИКЛЮЧЕНИЯ",
                "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ",
                "ВАЖНО", "ВАЖНО", "ВАЖНО"
        );

        game[12] = new ComputerGames(
                "Dying light, Dying light 2 Stay human",
                "ИГРОВОЙ", "ИГРОВОЙ", "ИГРОВОЙ",
                "5-20ГБ", "20-60ГБ", "5-20ГБ",
                "ПРИКЛЮЧЕНИЯ", "ПРИКЛЮЧЕНИЯ", "ПРИКЛЮЧЕНИЯ",
                "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ",
                "ВАЖНО", "ВАЖНО", "ВАЖНО"
        );

        game[13] = new ComputerGames(
                "Forza Horizon 4,5",
                "ИГРОВОЙ", "ИГРОВОЙ", "ИГРОВОЙ",
                "20-60ГБ", "20-60ГБ", "20-60ГБ",
                "СИМУЛЯТОР", "СИМУЛЯТОР", "СИМУЛЯТОР",
                "МНОГОПОЛЬЗОВАТЕЛЬСКУЮ", "МНОГОПОЛЬЗОВАТЕЛЬСКУЮ", "МНОГОПОЛЬЗОВАТЕЛЬСКУЮ",
                "ВАЖНО", "ВАЖНО", "ВАЖНО"
        );

        game[14] = new ComputerGames(
                "Raft",
                "НЕИГРОВОЙ", "НЕИГРОВОЙ", "НЕИГРОВОЙ",
                "5-20ГБ", "5-20ГБ", "5-20ГБ",
                "ПРИКЛЮЧЕНИЯ", "ПРИКЛЮЧЕНИЯ", "ПРИКЛЮЧЕНИЯ",
                "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ",
                "НЕ ВАЖНО", "НЕ ВАЖНО", "НЕ ВАЖНО"
        );

        game[15] = new ComputerGames(
                "Sea of thieves",
                "ИГРОВОЙ", "ИГРОВОЙ", "ИГРОВОЙ",
                "60-80ГБ", "60-80ГБ", "60-80ГБ",
                "ПРИКЛЮЧЕНИЯ", "ПРИКЛЮЧЕНИЯ", "ПРИКЛЮЧЕНИЯ",
                "МНОГОПОЛЬЗОВАТЕЛЬСКУЮ", "МНОГОПОЛЬЗОВАТЕЛЬСКУЮ", "МНОГОПОЛЬЗОВАТЕЛЬСКУЮ",
                "ВАЖНО", "ВАЖНО", "ВАЖНО"
        );

        game[16] = new ComputerGames(
                "Horizon zero Dawn",
                "ИГРОВОЙ", "ИГРОВОЙ", "ИГРОВОЙ",
                "60-80ГБ", "60-80ГБ", "60-80ГБ",
                "ПРИКЛЮЧЕНИЯ", "ПРИКЛЮЧЕНИЯ", "ПРИКЛЮЧЕНИЯ",
                "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ",
                "ВАЖНО", "ВАЖНО", "ВАЖНО"
        );

        game[17] = new ComputerGames(
                "Outlast, Outlast 2",
                "ИГРОВОЙ", "НЕИГРОВОЙ", "ИГРОВОЙ",
                "5-20ГБ", "5-20ГБ", "5-20ГБ",
                "УЖАСЫ", "УЖАСЫ", "УЖАСЫ",
                "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ",
                "НЕ ВАЖНО", "НЕ ВАЖНО", "НЕ ВАЖНО"
        );

        game[18] = new ComputerGames(
                "Far Cry 3,4,5,6, Primal, New Down",
                "ИГРОВОЙ", "НЕИГРОВОЙ", "ИГРОВОЙ",
                "5-20ГБ", "20-60ГБ", "60-80ГБ",
                "ПРИКЛЮЧЕНИЯ", "ПРИКЛЮЧЕНИЯ", "ПРИКЛЮЧЕНИЯ",
                "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ",
                "НЕ ВАЖНО", "ВАЖНО", "НЕ ВАЖНО"
        );

        game[19] = new ComputerGames(
                "Portal, Portal 2",
                "НЕИГРОВОЙ", "НЕИГРОВОЙ", "НЕИГРОВОЙ",
                "до 5ГБ", "до 5ГБ", "до 5ГБ",
                "ГОЛОВОЛОМКА", "ГОЛОВОЛОМКА", "ГОЛОВОЛОМКА",
                "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ",
                "НЕ ВАЖНО", "НЕ ВАЖНО", "НЕ ВАЖНО"
        );

        game[20] = new ComputerGames(
                "7 Days to die",
                "НЕИГРОВОЙ", "НЕИГРОВОЙ", "НЕИГРОВОЙ",
                "5-20ГБ", "5-20ГБ", "5-20ГБ",
                "ПРИКЛЮЧЕНИЯ", "ПРИКЛЮЧЕНИЯ", "ПРИКЛЮЧЕНИЯ",
                "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ",
                "НЕ ВАЖНО", "НЕ ВАЖНО", "НЕ ВАЖНО"
        );

        game[21] = new ComputerGames(
                "The forest",
                "НЕИГРОВОЙ", "НЕИГРОВОЙ", "НЕИГРОВОЙ",
                "5-20ГБ", "5-20Б", "5-20ГБ",
                "ПРИКЛЮЧЕНИЯ", "ПРИКЛЮЧЕНИЯ", "ПРИКЛЮЧЕНИЯ",
                "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ",
                "ВАЖНО", "ВАЖНО", "ВАЖНО"
        );

        game[22] = new ComputerGames(
                "Sons of The forest",
                "ИГРОВОЙ", "ИГРОВОЙ", "ИГРОВОЙ",
                "5-20ГБ", "5-20ГБ", "5-20ГБ",
                "ПРИКЛЮЧЕНИЯ", "ПРИКЛЮЧЕНИЯ", "ПРИКЛЮЧЕНИЯ",
                "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ",
                "ВАЖНО", "ВАЖНО", "ВАЖНО"
        );
        game[23] = new ComputerGames(
                "Minecraft",
                "НЕИГРОВОЙ", "НЕИГРОВОЙ", "НЕИГРОВОЙ",
                "до 5ГБ", "до 5ГБ", "до 5ГБ",
                "ПРИКЛЮЧЕНИЯ", "ПРИКЛЮЧЕНИЯ", "ПРИКЛЮЧЕНИЯ",
                "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "МНОГОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ",
                "НЕ ВАЖНО", "НЕ ВАЖНО", "НЕ ВАЖНО"
        );

        game[24] = new ComputerGames(
                "Call of duty Black Ops 3,Black Ops 2,WW2,modern warfare 2",
                "ИГРОВОЙ", "ИГРОВОЙ", "ИГРОВОЙ",
                "60-80ГБ", "60-80ГБ", "60-80ГБ",
                "ЭКШЕН", "ЭКШЕН", "ЭКШЕН",
                "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "МНОГОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ",
                "ВАЖНО", "ВАЖНО", "ВАЖНО"
        );

        game[25] = new ComputerGames(
                "Stalker Shadow of Chernobyl, Clear Sky,Call of Pripyat,Heart of Chernobyl",
                "НЕИГРОВОЙ", "НЕИГРОВОЙ", "НЕИГРОВОЙ",
                "до 5ГБ", "5-20ГБ", "до 5ГБ",
                "ПРИКЛЮЧЕНИЯ", "ПРИКЛЮЧЕНИЯ", "ПРИКЛЮЧЕНИЯ",
                "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ",
                "НЕ ВАЖНО", "НЕ ВАЖНО", "НЕ ВАЖНО"
        );

        game[26] = new ComputerGames(
                "Metro Exodus,Last Light",
                "ИГРОВОЙ", "НИГРОВОЙ", "ИГРОВОЙ",
                "5-20ГБ", "5-20ГБ", "5-20ГБ",
                "ЭКШЕН", "ЭКШЕН", "ЭКШЕН",
                "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ", "ОДНОПОЛЬЗОВАТЕЛЬСКУЮ",
                "ВАЖНО", "ВАЖНО", "ВАЖНО"
        );

        game[27] = new ComputerGames(
                "Rust",
                "ИГРОВОЙ", "НИГРОВОЙ", "ИГРОВОЙ",
                "5-20ГБ", "5-20ГБ", "5-20ГБ",
                "ПРИКЛЮЧЕНИЯ", "ПРИКЛЮЧЕНИЯ", "ПРИКЛЮЧЕНИЯ",
                "МНОГОПОЛЬЗОВАТЕЛЬСКУЮ", "МНОГОПОЛЬЗОВАТЕЛЬСКУЮ", "МНОГОПОЛЬЗОВАТЕЛЬСКУЮ",
                "ВАЖНО", "ВАЖНО", "ВАЖНО"
        );

        System.out.println("answers = " + Arrays.toString(answers));
        sendMessage(chatId, "Я рекомендую Вам следующте игры:");
        int numberOfSelectedGames = 0;
        for (int i = 0; i < 28; i++) {
            int gameSelectedFlag = 1;
            System.out.println(i + " " + game[i].name);
            for (int j = 0; j < 5; j++) {
                System.out.println("Answer=" + answers[j] +
                        " Key0=" + game[i].keys[j][0] +
                        " Key1=" + game[i].keys[j][1] +
                        " Key2=" + game[i].keys[j][2]);
                if (!answers[j].equals(game[i].keys[j][0]) && !answers[j].equals(game[i].keys[j][1]) && !answers[j].equals(game[i].keys[j][2])) {
                    gameSelectedFlag = 0;
                    System.out.println("Игра не выбрана");
                }
            }
            if(gameSelectedFlag == 1) {
                String message = ++numberOfSelectedGames + " " + game[i].name;
                sendMessage(chatId, message);
            }
        }
        if(numberOfSelectedGames == 0) {
            sendMessage(chatId, "Подходящих Вам игр НЕТ - готовьтесь к ОГЭ !!!");
        }

    }





    public void answerQuestionFive (long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("ВОПРОС 5: Важно ли Вам качество графики?");

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("ВАЖНО");
        button1.setCallbackData("ВАЖНО");

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(button1);

        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("НЕВАЖНО");
        button2.setCallbackData("НЕВАЖНО");

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(button2);

        List<List<InlineKeyboardButton>>  rowList = new ArrayList<>();
        rowList.add(row1);
        rowList.add(row2);

        inlineKeyboardMarkup.setKeyboard(rowList);
        message.setReplyMarkup(inlineKeyboardMarkup);


        try {
            execute(message);
        } catch (TelegramApiException e) {

        }
    } // end of question
    public void answerQuestionFour (long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("ВОПРОС 4: Вы хотите сыграть в однопользовательскую или многопользовательскую игру ?");

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("ОДНОПОЛЬЗОВАТЕЛЬСКУЮ");
        button1.setCallbackData("ОДНОПОЛЬЗОВАТЕЛЬСКУЮ");

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(button1);

        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("МНОГОПОЛЬЗОВАТЕЛЬСКУЮ");
        button2.setCallbackData("МНОГОПОЛЬЗОВАТЕЛЬСКУЮ");

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(button2);

        List<List<InlineKeyboardButton>>  rowList = new ArrayList<>();
        rowList.add(row1);
        rowList.add(row2);

        inlineKeyboardMarkup.setKeyboard(rowList);
        message.setReplyMarkup(inlineKeyboardMarkup);


        try {
            execute(message);
        } catch (TelegramApiException e) {

        }
    } // end of question

    public void answerQuestionThree (long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("ВОПРОС 3: В игру какого жанра Вы хотите сыграть ?");

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("СТРАТЕГИЯ");
        button1.setCallbackData("СТРАТЕГИЯ");
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("ЭКШЕН");
        button2.setCallbackData("ЭКШЕН");

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(button1);
        row1.add(button2);

        InlineKeyboardButton button3 = new InlineKeyboardButton();
        button3.setText("ПРИКЛЮЧЕНИЯ");
        button3.setCallbackData("ПРИКЛЮЧЕНИЯ");
        InlineKeyboardButton button4 = new InlineKeyboardButton();
        button4.setText("СИМУЛЯТОР");
        button4.setCallbackData("СИМУЛЯТОР");

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(button3);
        row2.add(button4);

        InlineKeyboardButton button5 = new InlineKeyboardButton();
        button5.setText("ГОЛОВОЛОМКА");
        button5.setCallbackData("ГОЛОВОЛОМКА");
        InlineKeyboardButton button6 = new InlineKeyboardButton();
        button6.setText("УЖАСЫ");
        button6.setCallbackData("УЖАСЫ");

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(button5);
        row3.add(button6);

        List<List<InlineKeyboardButton>>  rowList = new ArrayList<>();
        rowList.add(row1);
        rowList.add(row2);
        rowList.add(row3);

        inlineKeyboardMarkup.setKeyboard(rowList);
        message.setReplyMarkup(inlineKeyboardMarkup);


        try {
            execute(message);
        } catch (TelegramApiException e) {

        }
    } // end of question


    public void answerQuestionTwo (long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("ВОПРОС 2: Сколько места на компьютере Вы можете выделить под игру ?");

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("до 5ГБ");
        button1.setCallbackData("до 5ГБ");
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("5-20ГБ");
        button2.setCallbackData("5-20ГБ");

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(button1);
        row1.add(button2);

        InlineKeyboardButton button3 = new InlineKeyboardButton();
        button3.setText("20-60ГБ");
        button3.setCallbackData("20-60ГБ");
        InlineKeyboardButton button4 = new InlineKeyboardButton();
        button4.setText("60-80ГБ");
        button4.setCallbackData("60-80ГБ");

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(button3);
        row2.add(button4);

        InlineKeyboardButton button5 = new InlineKeyboardButton();
        button5.setText("более 80ГБ");
        button5.setCallbackData("более 80ГБ");

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(button5);


        List<List<InlineKeyboardButton>>  rowList = new ArrayList<>();
        rowList.add(row1);
        rowList.add(row2);
        rowList.add(row3);

        inlineKeyboardMarkup.setKeyboard(rowList);
        message.setReplyMarkup(inlineKeyboardMarkup);


        try {
            execute(message);
        } catch (TelegramApiException e) {

        }
    } // end of question


   public void answerQuestionOne (long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("ВОПРОС 1: Какой у Вас компьютер ?");

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("ИГРОВОЙ");
        button1.setCallbackData("ИГРОВОЙ");
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(button1);

        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("НЕИГРОВОЙ");
        button2.setCallbackData("НЕИГРОВОЙ");
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(button2);

       List<List<InlineKeyboardButton>>  rowList = new ArrayList<>();
       rowList.add(row1);
       rowList.add(row2);

       inlineKeyboardMarkup.setKeyboard(rowList);
       message.setReplyMarkup(inlineKeyboardMarkup);


       try {
           execute(message);
       } catch (TelegramApiException e) {

       }
   } // end of question




    private void startCommandReceived(long chatId, String name) {
        sendMessage(chatId, "Проект: БОТ РЕКОМЕНДУЕТ ИГРУ. Авторы: Альберт А., Саша Г., Игорь В., Мария Ф");
        String answer = "Привет, " + name + ", рад Вас видеть!";
        sendMessage(chatId, answer);
        sendMessage(chatId, "Ответьте на 5 вопросов и я порекомендую Вам игры");
   //     sendMessage(chatId, "Отвечать на вопросы нужно только нажатием кнопок");
    } // end start

    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSend);
        try{
            execute(message);
        }
        catch (TelegramApiException e) {

        }

    } // end send message

}
