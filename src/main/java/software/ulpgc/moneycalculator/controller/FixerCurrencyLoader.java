package software.ulpgc.moneycalculator.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import software.ulpgc.moneycalculator.interfaces.CurrencyLoader;
import software.ulpgc.moneycalculator.model.Currency;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

public class FixerCurrencyLoader implements CurrencyLoader {
    @Override
    public List<Currency> load() {
        try {
            return toList(loadJson());
        } catch (IOException e) {
            return emptyList();
        }
    }

    private List<Currency> toList(String json) {

        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);

        if (!jsonObject.has("result")) {
            JsonErrorHandler.handleUnexpectedResponse();
            return emptyList();
        }
        if (!jsonObject.get("result").getAsString().equals("success")) {
            JsonErrorHandler.handleErrorResponse(jsonObject);
            return emptyList();
        }

        return currenciesFrom(jsonObject);
    }


    private List<Currency> currenciesFrom(JsonObject json) {
        List<Currency> supportedCurrencies = json.get("supported_codes")
                .getAsJsonArray().asList().stream()
                .map(currency -> {
                    JsonArray currencyArray = currency.getAsJsonArray();
                    return new Currency(currencyArray.get(0).getAsString(), currencyArray.get(1).getAsString());
                }).collect(Collectors.toList());
        return supportedCurrencies;
    }



    private String loadJson() throws IOException {
        URL url = new URL("https://v6.exchangerate-api.com/v6/" + FixerAPI.key + "/codes" );
        try (InputStream is = url.openStream()) {
            return new String(is.readAllBytes());
        }
    }
}
