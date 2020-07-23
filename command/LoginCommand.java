package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH  +
            "login_en");
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH  +
            "verifiedCards");


    @Override
    public void execute() throws InterruptOperationException {

        String cardNumber;
        String pin;

        ConsoleHelper.writeMessage(res.getString("before"));

        while (true) {

            ConsoleHelper.writeMessage(res.getString("specify.data"));
            cardNumber = ConsoleHelper.readString();
            pin = ConsoleHelper.readString();

            if (validCreditCards.containsKey(cardNumber)) {

                if (validCreditCards.getString(cardNumber).equals(pin)) {
                    ConsoleHelper.writeMessage(res.getString("success.format"));
                    break;
                } else {
                    ConsoleHelper.writeMessage(res.getString("not.verified.format"));
                    ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                }
            } else {
                ConsoleHelper.writeMessage(res.getString("not.verified.format"));
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            }
        }

    }
}
