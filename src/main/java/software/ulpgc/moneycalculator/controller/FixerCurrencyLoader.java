package software.ulpgc.moneycalculator.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.interfaces.CurrencyLoader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        if (!jsonObject.has("success")) {
            JsonErrorHandler.handleUnexpectedResponse();
            return emptyList();
        }
        if (!jsonObject.get("success").getAsBoolean()) {
            JsonErrorHandler.handleErrorResponse(jsonObject);
            return emptyList();
        }

        return currenciesFrom(jsonObject);
    }


    private List<Currency> currenciesFrom(JsonObject json) {
        List<Currency> list = new ArrayList<>();
        Map<String, JsonElement> symbols = json.get("symbols").getAsJsonObject().asMap();
        for (String symbol : symbols.keySet()) {
            list.add(new Currency(symbol, symbols.get(symbol).getAsString()));
        }
        return list;
    }



    private String loadJson() throws IOException {
        URL url = new URL("http://data.fixer.io/api/symbols?access_key=" + FixerAPI.key);
        try (InputStream is = url.openStream()) {
            return new String(is.readAllBytes());
        }
    }
}
