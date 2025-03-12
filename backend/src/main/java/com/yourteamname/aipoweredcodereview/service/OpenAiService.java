package com.yourteamname.aipoweredcodereview.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import io.github.cdimascio.dotenv.Dotenv;
import reactor.core.publisher.Mono;

@Service
public class OpenAiService {

    private final WebClient webClient;
    private final String openAiApiKey;

    public OpenAiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.openai.com/v1").build();
        
        Dotenv dotenv = Dotenv.load();
        this.openAiApiKey = dotenv.get("OPENAI_API_KEY");

        if (this.openAiApiKey == null || this.openAiApiKey.isEmpty()) {
            throw new IllegalStateException("OpenAI API key is missing! Make sure it's set in the .env file.");
        }
    }

    public Mono<String> analyzeCode(String code) {
        return webClient.post()
            .uri("/chat/completions")  // ✅ Correct OpenAI endpoint
            .header("Authorization", "Bearer " + openAiApiKey)
            .header("Content-Type", "application/json")
            .bodyValue(Map.of(
                "model", "gpt-3.5-turbo-1106",  // ✅ Use latest version
                "messages", List.of(
                    Map.of("role", "system", "content", "You are an AI code reviewer. Analyze the given Java code."),
                    Map.of("role", "user", "content", code)
                ),
                "max_tokens", 500
            ))
            .retrieve()
            .onStatus(status -> status.value() >= 400, response ->  // ✅ FIX: Proper status check
                response.bodyToMono(String.class).flatMap(errorBody -> {
                    System.err.println("OpenAI API Error: " + errorBody);
                    return Mono.error(new RuntimeException("OpenAI API Error: " + errorBody));
                })
            )
            .bodyToMono(String.class);
    }
}
