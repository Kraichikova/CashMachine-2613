package com.javarush.task.task26.task2613;


import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.common_en");

    public static void writeMessage(String message) {
        System.out.println(message);
    }


    public static String readString() throws InterruptOperationException {

        String message = "";
        try {
            message = bis.readLine();
            if (message.equalsIgnoreCase(res.getString("operation.EXIT")))
                throw new InterruptOperationException();
        }
        catch (IOException ignored)
        {
        }
        return message;
    }


    public static String askCurrencyCode() throws InterruptOperationException {

        String s;
        while (true) {
            writeMessage(res.getString("choose.currency.code"));
            s = readString();
            if (s.length() != 3) {
                writeMessage(res.getString("invalid.data"));
            }
            else {
                s = s.toUpperCase();
                break;

            }

        }
        return s;
    }


    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {

        String[] array;
        writeMessage(res.getString("choose.denomination.and.count.format"));

        while (true)
        {
            String s = readString();
            array = s.split(" ");
            int k;
            int l;
            try
            {
                k = Integer.parseInt(array[0]);
                l = Integer.parseInt(array[1]);
            }
            catch (Exception e)
            {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            if (k <= 0 || l <= 0 || array.length > 2)
            {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            break;
        }
        return array;
    }


    public static Operation askOperation() throws InterruptOperationException {

        while (true)
        {
            String line = readString();
            if (Integer.parseInt(line) > 0 && Integer.parseInt(line) < 5)
                return Operation.getAllowableOperationByOrdinal(Integer.parseInt(line));
            else
                writeMessage(res.getString("invalid.data"));
        }
    }


    public static void printExitMessage() {

        ConsoleHelper.writeMessage(res.getString("the.end"));
    }

}
