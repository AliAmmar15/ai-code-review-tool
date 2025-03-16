package com.yourteamname.aipoweredcodereview.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yourteamname.aipoweredcodereview.service.OpenAiService;

@RestController
@RequestMapping("/api")
public class CodeAnalysisController {

    private final OpenAiService openAiService;

    public CodeAnalysisController(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    @PostMapping("/code-review")
    public ResponseEntity<String> analyzeCode(@RequestBody Map<String, String> request) {
        String code = request.get("code");
        String feedback = openAiService.analyzeCode(code).block(); // Synchronous execution
        return ResponseEntity.ok(feedback); // Ensure response is plain text
    }
}
