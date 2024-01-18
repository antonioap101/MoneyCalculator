package software.ulpgc.moneycalculator.mocks;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.Money;
import software.ulpgc.moneycalculator.interfaces.MoneyDialog;

import java.util.List;


public class MockMoneyDialog implements MoneyDialog {
    private List<Currency> currencies;

    @Override
    public MoneyDialog define(List<Currency> currencies) {
        this.currencies = currencies;
        return this;
    }

    @Override
    public Money get() {
        return new Money(200, currencies.get(0));
    }
}
