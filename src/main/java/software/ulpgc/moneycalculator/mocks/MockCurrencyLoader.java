package software.ulpgc.moneycalculator.mocks;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.interfaces.CurrencyLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MockCurrencyLoader implements CurrencyLoader {
    @Override
    public List<Currency> load() {
        List<Currency> currencies = new ArrayList<>();

        // Cargar el archivo como un recurso
        try (InputStream inputStream = getClass().getResourceAsStream("/currencies.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); //
                if (parts.length >= 2) {
                    String code = parts[0].trim();
                    String name = parts[1].trim();
                    currencies.add(new Currency(code, name));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return currencies;
    }
}
