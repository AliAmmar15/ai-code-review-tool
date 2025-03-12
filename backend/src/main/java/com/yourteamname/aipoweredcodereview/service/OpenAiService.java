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
                    Map.of("role", "system", "content", "You are an advanced AI code reviewer specializing in Java. Analyze the given Java code thoroughly, identifying any bugs, inefficiencies, or deviations from best practices. Evaluate its correctness and detect potential runtime errors while also considering performance optimizations that could improve efficiency. Ensure that the code adheres to Java best practices and established design patterns, while also assessing any security vulnerabilities or potential risks. Additionally, review the readability and maintainability of the code, suggesting improvements where necessary, including better documentation or refactoring if needed. If there are alternative implementations that enhance clarity or performance, provide explanations and recommendations. Present your feedback in a structured and detailed manner, ensuring that the developer understands the reasoning behind each suggestion and how to apply the improvements effectively. but also get it nice and simple and easy to understand for the developer."),
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
