package software.ulpgc.moneycalculator.mocks;

import software.ulpgc.moneycalculator.model.Money;
import software.ulpgc.moneycalculator.interfaces.MoneyDisplay;

public class MockMoneyDisplay implements MoneyDisplay {
    @Override
    public void show(Money money) {
        System.out.println(money);
    }
}
