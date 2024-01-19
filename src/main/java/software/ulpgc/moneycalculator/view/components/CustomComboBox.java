package software.ulpgc.moneycalculator.view.components;

import software.ulpgc.moneycalculator.view.theme.ColorTheme;

import javax.swing.*;
import java.awt.*;

public class CustomComboBox<T> extends JComboBox<T> {
    private final JPanel contentPanel;

    public CustomComboBox(String labelText) {
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(ColorTheme.COMBOBOX_BACKGROUND_COLOR);

        JLabel label = new JLabel(labelText);
        label.setForeground(ColorTheme.COMBOBOX_LABEL_COLOR);
        label.setFont(new Font("Arial", Font.BOLD, 12));

        setBackground(ColorTheme.COMBOBOX_BACKGROUND_COLOR);
        setForeground(ColorTheme.COMBOBOX_FOREGROUND_COLOR);
        setFont(new Font("Arial", Font.BOLD, 14));
        setBorder(null);

        contentPanel.add(label, BorderLayout.NORTH);
        contentPanel.add(this, BorderLayout.CENTER);
    }

    public JPanel getComponent(){
        return contentPanel;
    }


}