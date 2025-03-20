package com.yourteamname.aipoweredcodereview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class AiPoweredCodeReviewApplication {
    public static void main(String[] args) {
        // Load environment variables from .env file
        Dotenv dotenv = Dotenv.configure()
                .directory("./backend") // Ensure the path is correct
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();

        // Get API key safely
        String apiKey = dotenv.get("OPENAI_API_KEY");

        if (apiKey == null || apiKey.isEmpty()) {
            System.err.println("❌ ERROR: OPENAI_API_KEY is not set. Please check your .env file.");
        } else {
            System.setProperty("openai.api.key", apiKey);
            System.out.println("Successfully loaded API Key.");
        }

        SpringApplication.run(AiPoweredCodeReviewApplication.class, args);
    }
}
