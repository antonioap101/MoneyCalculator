package software.ulpgc.moneycalculator.model;

import java.util.Locale;

public record Money(double amount, Currency currency) {

    @Override
    public String toString() {
        return String.format(Locale.US, "%.2f", amount) + " " + currency;
    }
}
