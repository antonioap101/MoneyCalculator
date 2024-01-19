package software.ulpgc.moneycalculator.view.components;

import software.ulpgc.moneycalculator.view.theme.ColorTheme;

import javax.swing.*;
import java.awt.*;


public class CustomTextField extends JTextField {
    public CustomTextField() {
        setFont(new Font("Arial", Font.BOLD, 14));
        setHorizontalAlignment(JTextField.CENTER);
        setBackground(ColorTheme.TEXTFIELD_BACKGROUND_COLOR);
        setBorder(null);
    }
}
