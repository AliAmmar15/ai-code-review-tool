package com.yourteamname.aipoweredcodereview.controller;

import com.yourteamname.aipoweredcodereview.service.OpenAiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CodeAnalysisController {

    private final OpenAiService openAiService;

    public CodeAnalysisController(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    @PostMapping("/code-review")
    public String analyzeCode(@RequestBody String code) {
        return openAiService.analyzeCode(code).block(); // Fix: Ensure it's a Mono<String>
    }
}
