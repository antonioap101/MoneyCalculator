package software.ulpgc.moneycalculator.view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import software.ulpgc.moneycalculator.view.theme.ColorTheme;

public class CustomButtonPanel extends JPanel {
    private final JButton button;

    private CustomButtonPanel(String text) {
        button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(ColorTheme.BUTTON_BACKGROUND_COLOR);
        button.setForeground(ColorTheme.BUTTON_FOREGROUND_COLOR);
        button.setPreferredSize(new Dimension(200, 50));

        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        setBackground(ColorTheme.BACKGROUND_COLOR);
        add(button);
    }

    public static CustomButtonPanel create(String text){
        return new CustomButtonPanel(text);
    }

    public void addActionListener(ActionListener l) {
        button.addActionListener(l);
    }

}
