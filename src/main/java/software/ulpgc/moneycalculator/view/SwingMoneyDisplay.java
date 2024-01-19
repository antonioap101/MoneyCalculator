package software.ulpgc.moneycalculator.view;

import software.ulpgc.moneycalculator.model.Money;
import software.ulpgc.moneycalculator.interfaces.MoneyDisplay;
import software.ulpgc.moneycalculator.view.theme.ColorTheme;

import javax.swing.*;
import java.awt.*;

public class SwingMoneyDisplay extends JPanel implements MoneyDisplay {
    private final JLabel label;

    public SwingMoneyDisplay() {
        this.label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setForeground(Color.BLACK);

        setLayout(new BorderLayout(0,0));
        setBackground(ColorTheme.BACKGROUND_COLOR);
        add(label, BorderLayout.CENTER);

    }

    @Override
    public void show(Money money) {
        label.setText(money.toString());
    }
}
