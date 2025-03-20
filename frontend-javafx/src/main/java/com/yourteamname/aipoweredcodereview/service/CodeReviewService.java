package com.yourteamname.aipoweredcodereview.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CodeReviewService {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public String reviewCode(String code) {
        try {
            URL url = new URL("http://localhost:8080/api/analyze");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Create request body
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("code", code);

            // Convert to JSON using Jackson
            String jsonInputString = objectMapper.writeValueAsString(requestBody);

            // Send request
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Handle response
            if (conn.getResponseCode() >= 400) {
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8))) {
                    return "Error: " + br.lines().collect(Collectors.joining("\n"));
                }
            }

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                return br.lines().collect(Collectors.joining("\n"));
            }

        } catch (Exception e) {
            return "Error occurred: " + e.getMessage();
        }
    }
}