package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH  +
            "deposit_en");

    @Override
    public void execute() throws InterruptOperationException {

        ConsoleHelper.writeMessage(res.getString("before"));
        String code = ConsoleHelper.askCurrencyCode();
        String[] arg = new String[0];
        try {
            arg = ConsoleHelper.getValidTwoDigits(code);
            ConsoleHelper.writeMessage(String.format(res.getString("success.format"), Integer.parseInt(arg[0]), arg[1]));

        } catch (InterruptOperationException e) {
            ConsoleHelper.writeMessage(res.getString("invalid.data"));
        }
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        currencyManipulator.addAmount(Integer.parseInt(arg[0]), Integer.parseInt(arg[1]));

        System.out.println(currencyManipulator.getTotalAmount());
    }
}
