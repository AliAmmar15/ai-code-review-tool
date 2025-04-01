package com.yourteamname.aipoweredcodereview.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenAiService {

    @Value("${openai.api.key}")
    private String apiKey;

    private final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";
    private final RestTemplate restTemplate = new RestTemplate();

    public String analyzeCode(String code) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(apiKey);

            Map<String, Object> message = new HashMap<>();
            message.put("role", "user");
            message.put("content", "You are an AI-powered code review assistant. Please analyze the following code and provide a comprehensive review. Start by identifying any bugs or potential errors that could affect functionality or cause unexpected behavior. Then, examine the code for performance issues, such as inefficient logic, unnecessary memory consumption, or slow operations. Point out any areas where best practices or clean coding principles (such as DRY, KISS, or SOLID) are not followed. If there are any security concerns—like unsafe input handling, insecure data storage, or potential vulnerabilities—highlight them clearly. Suggest actionable improvements to enhance the code’s quality, maintainability, and readability. This may include better naming, clearer structure, or possible refactoring. Also, comment on the overall readability, including formatting, indentation, and use of comments. If possible, give the code a quality score from 1 to 10 and summarize your main feedback points. Structure your response in organized sections so it's easy for the developer to review and apply. Then provide the improved code, Here's the code for analysis:\n" + code);

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("model", "gpt-3.5-turbo");
            requestBody.put("messages", List.of(message));
            requestBody.put("temperature", 0.7);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

            ResponseEntity<Map> response = restTemplate.postForEntity(
                OPENAI_API_URL,
                request,
                Map.class
            );

            if (response.getBody() != null) {
                List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
                if (!choices.isEmpty()) {
                    Map<String, Object> choice = choices.get(0);
                    Map<String, String> messageResponse = (Map<String, String>) choice.get("message");
                    return messageResponse.get("content");
                }
            }

            return "No analysis available.";
        } catch (Exception e) {
            return "Error analyzing code: " + e.getMessage();
        }
    }
}