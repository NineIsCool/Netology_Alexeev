package ru.netology.Nikita_Alexeev.domain.operation;

public enum Currency {
    RUB, USD;

    public static String parseCurrency(Currency currency) {
        switch (currency) {
            case RUB:
                return "рублей";
            case USD:
                return "долларов";
            default:
                return null;
        }
    }
}
