package com.yourteamname.aipoweredcodereview.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yourteamname.aipoweredcodereview.service.OpenAiService;

@RestController
@RequestMapping("/api/code-review")
public class CodeAnalysisController {

    private final OpenAiService openAiService;

    public CodeAnalysisController(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    @PostMapping
    public ResponseEntity<String> analyzeCode(@RequestBody Map<String, String> request) {
        String code = request.get("code");
        if (code == null || code.isEmpty()) {
            return ResponseEntity.badRequest().body("Code input is empty.");
        }

        // Call OpenAI API and get raw response
        String response = openAiService.analyzeCode(code).block();

        // Return AI response as plain text
        return ResponseEntity.ok(response);
    }
}
