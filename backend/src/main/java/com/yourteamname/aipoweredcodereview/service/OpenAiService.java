package com.yourteamname.aipoweredcodereview.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class OpenAiService {

    private final WebClient webClient;

    @Value("${openai.api.key}")
    private String openAiApiKey;

    public OpenAiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.openai.com/v1").build();
    }

    public Mono<String> analyzeCode(String code) {
        return webClient.post()
            .uri("/completions")
            .header("Authorization", "Bearer " + openAiApiKey)
            .bodyValue("{ \"model\": \"gpt-4\", \"prompt\": \"" + code + "\", \"max_tokens\": 500 }")
            .retrieve()
            .bodyToMono(String.class);
    }
}
