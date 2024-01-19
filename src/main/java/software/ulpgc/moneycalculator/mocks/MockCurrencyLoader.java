package software.ulpgc.moneycalculator.mocks;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.interfaces.CurrencyLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

public class MockCurrencyLoader implements CurrencyLoader {
    @Override
    public List<Currency> load() {
        try (InputStream inputStream = getClass().getResourceAsStream("/currencies.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return currenciesFrom(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return emptyList();
    }

    private List<Currency> currenciesFrom(BufferedReader reader) throws IOException {
        List<Currency> currencies = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            currencies.add(new Currency(parts[0].trim(), parts[1].trim()));
        }
        return currencies;
    }
}