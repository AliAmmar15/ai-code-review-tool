package com.yourteamname.aipoweredcodereview.controller;

import com.yourteamname.aipoweredcodereview.model.CodeReviewRequest;
import com.yourteamname.aipoweredcodereview.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CodeAnalysisController {

    private final OpenAiService openAiService;

    @Autowired
    public CodeAnalysisController(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    @PostMapping("/analyze")
    public ResponseEntity<String> analyzeCode(@RequestBody CodeReviewRequest request) {
        if (request.getCode() == null || request.getCode().isEmpty()) {
            return ResponseEntity.badRequest().body("Error: Code is missing in request.");
        }
        try {
            // Use your OpenAI service to analyze the code
            String analysis = openAiService.analyzeCode(request.getCode());
            return ResponseEntity.ok(analysis);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error analyzing code: " + e.getMessage());
        }
    }
}