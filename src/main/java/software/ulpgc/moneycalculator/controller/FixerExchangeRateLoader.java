package software.ulpgc.moneycalculator.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import software.ulpgc.moneycalculator.interfaces.ExchangeRateLoader;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.ExchangeRate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;

public class FixerExchangeRateLoader implements ExchangeRateLoader {
    @Override
    public ExchangeRate load(Currency from, Currency to) {
        try {
            return new ExchangeRate(from, to, LocalDate.now(), toDouble(loadJson(from.toString(), to.toString())));
        } catch (IOException e) {
            return new ExchangeRate(from, to, LocalDate.now(), 0.0);
        }
    }

    double toDouble(String json) {
        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);

        if (!jsonObject.has("result")) {
            JsonErrorHandler.handleUnexpectedResponse();
            return 0.0;
        }
        if (!jsonObject.get("result").getAsString().equals("success")) {
            JsonErrorHandler.handleErrorResponse(jsonObject);
            return 0.0;
        }

        return rateFrom(jsonObject);
    }

    private double rateFrom(JsonObject jsonObject) {
        return jsonObject.get("conversion_rate").getAsDouble();
    }

    private String loadJson(String from, String to) throws IOException {

        URL url = new URL("https://v6.exchangerate-api.com/v6/"
                + FixerAPI.key + "/pair/" +
                from.split("-")[0] + "/" + to.split("-")[0]);

        try (InputStream is = url.openStream()) {
            return new String(is.readAllBytes());
        }
    }
}
