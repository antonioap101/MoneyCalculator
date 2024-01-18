package software.ulpgc.moneycalculator.interfaces;

import software.ulpgc.moneycalculator.model.Currency;

import java.util.List;

public interface CurrencyLoader {
    List<Currency> load();
}
