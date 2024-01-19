package software.ulpgc.moneycalculator.view;

import software.ulpgc.moneycalculator.interfaces.CurrencyDialog;
import software.ulpgc.moneycalculator.interfaces.MoneyDialog;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.Money;
import software.ulpgc.moneycalculator.view.components.CustomTextField;
import software.ulpgc.moneycalculator.view.theme.ColorTheme;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SwingMoneyDialog extends JPanel implements MoneyDialog {
    private JTextField amountField;
    private CurrencyDialog currencyDialog;

    public SwingMoneyDialog() {
        this.setLayout(new GridLayout(2, 1));
        setBackground(ColorTheme.BACKGROUND_COLOR);
    }

    @Override
    public MoneyDialog define(List<Currency> currencies) {
        add(createAmountField());
        add(createCurrencyDialog(currencies));
        return this;
    }

    private Component createCurrencyDialog(List<Currency> currencies) {
        SwingCurrencyDialog dialog = new SwingCurrencyDialog("From");
        dialog.define(currencies);
        this.currencyDialog = dialog;
        return dialog;
    }

    private Component createAmountField() {
        this.amountField = new CustomTextField();
        return amountField;
    }

    @Override
    public Money get() {
        return new Money(toDouble(amountField.getText()), currencyDialog.get());
    }

    private double toDouble(String text) {
        return Double.parseDouble(text);
    }


}
