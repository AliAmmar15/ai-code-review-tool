package com.yourteamname.aipoweredcodereview.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class CodeReviewService {

    private static final String BACKEND_URL = "http://localhost:8080/api/code-review";

    public static String sendCodeForAnalysis(String code) {
        try {
            String jsonPayload = "{ \"code\": \"" + code.replace("\"", "\\\"") + "\" }";
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BACKEND_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonPayload, StandardCharsets.UTF_8))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
