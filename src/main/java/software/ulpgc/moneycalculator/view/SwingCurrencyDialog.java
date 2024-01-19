package software.ulpgc.moneycalculator.view;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.interfaces.CurrencyDialog;
import software.ulpgc.moneycalculator.view.components.CustomComboBox;
import software.ulpgc.moneycalculator.view.theme.ColorTheme;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SwingCurrencyDialog extends JPanel implements CurrencyDialog {

    private final String labelText;
    private CustomComboBox<Currency> currencySelector;

    public SwingCurrencyDialog(String labelText) {
        this.labelText = labelText;
        setBackground(ColorTheme.BACKGROUND_COLOR);
        setBackground(ColorTheme.BACKGROUND_COLOR);
    }

    @Override
    public CurrencyDialog define(List<Currency> currencies) {
        add(createCurrencySelector(currencies));
        return this;
    }

    private Component createCurrencySelector(List<Currency> currencies) {
        CustomComboBox<Currency> selector = new CustomComboBox<>(labelText);
        for (Currency currency : currencies) selector.addItem(currency);
        this.currencySelector = selector;
        return selector.getComponent();
    }

    @Override
    public Currency get() {
        return currencySelector.getItemAt(currencySelector.getSelectedIndex());
    }
}
