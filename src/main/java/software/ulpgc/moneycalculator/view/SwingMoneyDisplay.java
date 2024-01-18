package software.ulpgc.moneycalculator.view;

import software.ulpgc.moneycalculator.model.Money;
import software.ulpgc.moneycalculator.interfaces.MoneyDisplay;

import javax.swing.*;

public class SwingMoneyDisplay extends JLabel implements MoneyDisplay {
    @Override
    public void show(Money money) {
        this.setText(money.toString());
    }
}
