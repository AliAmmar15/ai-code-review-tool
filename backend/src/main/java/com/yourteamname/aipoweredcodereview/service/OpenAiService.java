package com.yourteamname.aipoweredcodereview.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class OpenAiService {

    private final WebClient webClient;

    // Constructor to initialize WebClient
    public OpenAiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.openai.com/v1").build();
    }

    public Mono<String> analyzeCode(String code) {
        return webClient.post()
                .uri("/completions") // OpenAI endpoint
                .header("Authorization", "Bearer placeholder_for_api_key") // Replace with actual API key
                .bodyValue("{ \"model\": \"gpt-4\", \"prompt\": \"" + code + "\", \"max_tokens\": 500 }")
                .retrieve()
                .bodyToMono(String.class);
    }
}
