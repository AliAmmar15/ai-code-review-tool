package com.yourteamname.aipoweredcodereview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;


@SpringBootApplication
public class AiPoweredCodeReviewApplication {
    public static void main(String[] args) {
        // Load environment variables from .env file
        Dotenv dotenv = Dotenv.load();
        System.setProperty("openai.api.key", dotenv.get("OPENAI_API_KEY"));

        SpringApplication.run(AiPoweredCodeReviewApplication.class, args);

        System.out.println("Loaded API Key: " + System.getenv("OPENAI_API_KEY"));

    }
}