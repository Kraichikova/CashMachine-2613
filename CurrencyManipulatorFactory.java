package com.javarush.task.task26.task2613;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> map=new HashMap<>();
    private CurrencyManipulatorFactory() {
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode)
    {
     //   isExist = false;
        CurrencyManipulator current;
        currencyCode = currencyCode.toUpperCase();

        if (map.containsKey(currencyCode))
            return map.get(currencyCode);
        else {
            current = new CurrencyManipulator(currencyCode);
            map.put(currencyCode, current);
            return current;
        }
    }
    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        return map.values();
    }
}
