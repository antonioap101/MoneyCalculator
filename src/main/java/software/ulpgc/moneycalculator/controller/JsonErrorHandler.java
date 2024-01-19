package software.ulpgc.moneycalculator.controller;

import com.google.gson.JsonObject;

public class JsonErrorHandler {

    public static final String RED_TEXT = "\u001B[31m";
    public static final String RESET_TEXT = "\u001B[0m";

    public static void handleUnexpectedResponse() {
        System.out.println(RED_TEXT + "Unexpected JSON Response!" + RESET_TEXT);
    }

    public static void handleErrorResponse(JsonObject jsonObject) {
        String errorMessage = jsonObject.get("error").getAsString();
        System.out.println(RED_TEXT + "API Error: " + errorMessage + RESET_TEXT);
    }
}

