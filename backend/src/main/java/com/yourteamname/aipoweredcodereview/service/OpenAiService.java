package com.yourteamname.aipoweredcodereview.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class OpenAiService {

    private final WebClient webClient;
    private final String openAiApiKey;

    public OpenAiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.openai.com/v1").build();
        
        // Load API key from environment variables
        this.openAiApiKey = System.getenv("OPENAI_API_KEY");

        if (this.openAiApiKey == null || this.openAiApiKey.isEmpty()) {
            throw new IllegalStateException("OpenAI API key is missing! Ensure it's set in the environment variables.");
        }
    }

    public Mono<String> analyzeCode(String code) {
        return webClient.post()
            .uri("/chat/completions")
            .header("Authorization", "Bearer " + openAiApiKey)
            .header("Content-Type", "application/json")
            .bodyValue(Map.of(
                "model", "gpt-3.5-turbo-1106",
                "messages", List.of(
                    Map.of("role", "system", "content", "You are an advanced AI code reviewer specializing in Java. Analyze the given Java code thoroughly, identifying bugs, inefficiencies, or deviations from best practices. Provide short and easy-to-understand feedback."),
                    Map.of("role", "user", "content", code)
                ),
                "max_tokens", 500
            ))
            .retrieve()
            .onStatus(status -> status.value() >= 400, response ->
                response.bodyToMono(String.class).flatMap(errorBody -> {
                    System.err.println("OpenAI API Error: " + errorBody);
                    return Mono.error(new RuntimeException("OpenAI API Error: " + errorBody));
                })
            )
            .bodyToMono(Map.class)
            .map(response -> {
                // Extract the actual AI response content
                List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
                if (choices != null && !choices.isEmpty()) {
                    Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                    return message.get("content").toString();
                }
                return "Error: No response from AI.";
            });
    }
}
