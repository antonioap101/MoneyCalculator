package software.ulpgc.moneycalculator;

import software.ulpgc.moneycalculator.controller.ExchangeMoneyCommand;
import software.ulpgc.moneycalculator.controller.FixerCurrencyLoader;
import software.ulpgc.moneycalculator.controller.FixerExchangeRateLoader;
import software.ulpgc.moneycalculator.interfaces.Command;
import software.ulpgc.moneycalculator.interfaces.CurrencyDialog;
import software.ulpgc.moneycalculator.interfaces.MoneyDialog;
import software.ulpgc.moneycalculator.interfaces.MoneyDisplay;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.view.SwingCurrencyDialog;
import software.ulpgc.moneycalculator.view.SwingMoneyDialog;
import software.ulpgc.moneycalculator.view.SwingMoneyDisplay;
import software.ulpgc.moneycalculator.view.components.CustomButtonPanel;
import software.ulpgc.moneycalculator.view.theme.ColorTheme;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends JFrame {
    private final Map<String, Command> commands = new HashMap<>();
    private MoneyDisplay moneyDisplay;
    private MoneyDialog moneyDialog;
    private CurrencyDialog currencyDialog;

    public static void main(String[] args) {
        Main main = new Main();
        main.setVisible(true);
    }

    public Main() {
        initFrame();
        initComponents();
        initCommands();
    }

    private void initFrame() {
        setTitle("Money Calculator");

        setSize(400, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        getContentPane().setBackground(ColorTheme.BACKGROUND_COLOR);
    }

    private void initComponents() {
        add(createMainPanel());
    }

    private void initCommands() {
        List<Currency> currencies = new FixerCurrencyLoader().load(); // MockCurrencyLoader()
        Command exchangeMoneyCommand = new ExchangeMoneyCommand(
                moneyDialog.define(currencies),
                currencyDialog.define(currencies),
                new FixerExchangeRateLoader(), // MockExchangeRateLoader()
                moneyDisplay
        );
        addCommand("exchange money", exchangeMoneyCommand);
    }

    private JPanel createMainPanel() throws HeadlessException {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(ColorTheme.BACKGROUND_COLOR);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weightx = 1;
        gbc.insets = new Insets(10, 10, 10, 10);

        mainPanel.add(createMoneyDialog(), gbc);
        mainPanel.add(createCurrencyDialog(), gbc);
        mainPanel.add(createButtons(), gbc);
        mainPanel.add(createMoneyDisplay(), gbc);

        return mainPanel;
    }

    private Component createButtons() {
        CustomButtonPanel calculateButton = CustomButtonPanel.create("Calculate");
        calculateButton.addActionListener(e -> commands.get("exchange money").execute());
        return calculateButton;
    }

    private Component createMoneyDisplay() {
        SwingMoneyDisplay display = new SwingMoneyDisplay();
        this.moneyDisplay = display;
        return display;
    }

    private Component createCurrencyDialog() {
        SwingCurrencyDialog dialog = new SwingCurrencyDialog("To");
        this.currencyDialog = dialog;
        return dialog;
    }

    private Component createMoneyDialog() {
        SwingMoneyDialog dialog = new SwingMoneyDialog();
        this.moneyDialog = dialog;
        return dialog;
    }

    private void addCommand(String name, Command command) {
        commands.put(name, command);
    }

}
