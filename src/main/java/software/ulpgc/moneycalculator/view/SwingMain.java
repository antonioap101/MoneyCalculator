package software.ulpgc.moneycalculator.view;

import software.ulpgc.moneycalculator.controller.ExchangeMoneyCommand;
import software.ulpgc.moneycalculator.controller.FixerCurrencyLoader;
import software.ulpgc.moneycalculator.controller.FixerExchangeRateLoader;
import software.ulpgc.moneycalculator.interfaces.Command;
import software.ulpgc.moneycalculator.interfaces.CurrencyDialog;
import software.ulpgc.moneycalculator.interfaces.MoneyDialog;
import software.ulpgc.moneycalculator.interfaces.MoneyDisplay;
import software.ulpgc.moneycalculator.model.Currency;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwingMain extends JFrame {
    private final Map<String, Command> commands = new HashMap<>();
    private MoneyDisplay moneyDisplay;
    private MoneyDialog moneyDialog;
    private CurrencyDialog currencyDialog;

    public static void main(String[] args) {
        SwingMain main = new SwingMain();
        main.setVisible(true);
    }

    public SwingMain() {
        initFrame();
        initComponents();
        initCommands();
    }

    private void initFrame() {
        setTitle("Money Calculator");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1)); // FlowLayout()
    }

    private void initComponents() {
        moneyDialog = new SwingMoneyDialog();
        currencyDialog = new SwingCurrencyDialog();
        moneyDisplay = new SwingMoneyDisplay();

        add(createMainPanel());
    }

    private void initCommands() {
        List<Currency> currencies = new FixerCurrencyLoader().load();
        Command exchangeMoneyCommand = new ExchangeMoneyCommand(
                moneyDialog.define(currencies),
                currencyDialog.define(currencies),
                new FixerExchangeRateLoader(), // MockExchangeRateLoader()
                moneyDisplay
        );
        addCommand("exchange money", exchangeMoneyCommand);
    }

    private JPanel createMainPanel() throws HeadlessException {
        JPanel mainPanel = new JPanel(new FlowLayout());
        mainPanel.add(createMoneyDialog());
        mainPanel.add(createCurrencyDialog());
        mainPanel.add(createToolbar());
        mainPanel.add(createMoneyDisplay());
        return mainPanel;
    }

    private Component createToolbar() {
        JButton button = new JButton("Calculate");
        button.addActionListener(e -> commands.get("exchange money").execute());
        return button;
    }

    private Component createMoneyDisplay() {
        SwingMoneyDisplay display = new SwingMoneyDisplay();
        this.moneyDisplay = display;
        return display;
    }

    private Component createCurrencyDialog() {
        SwingCurrencyDialog dialog = new SwingCurrencyDialog();
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

    private MoneyDisplay moneyDisplay() {
        return moneyDisplay;
    }

    private CurrencyDialog currencyDialog() {
        return currencyDialog;
    }

    private MoneyDialog moneyDialog() {
        return moneyDialog;
    }
}
