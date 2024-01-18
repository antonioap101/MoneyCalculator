package software.ulpgc.moneycalculator.controller;

import com.google.gson.JsonObject;

public class JsonErrorHandler {

    public static final String RED_TEXT = "\u001B[31m";
    public static final String RESET_TEXT = "\u001B[0m";

    public static void handleUnexpectedResponse() {
        System.out.println(RED_TEXT + "Unexpected JSON Response!" + RESET_TEXT);
    }

    public static void handleErrorResponse(JsonObject jsonObject) {
        JsonObject errorObject = jsonObject.getAsJsonObject("error");
        int errorCode = errorObject.get("code").getAsInt();
        String errorMessage = errorObject.get("info").getAsString();
        System.out.println(RED_TEXT + "API Error (Code " + errorCode + "): " + errorMessage + RESET_TEXT);
    }
}

