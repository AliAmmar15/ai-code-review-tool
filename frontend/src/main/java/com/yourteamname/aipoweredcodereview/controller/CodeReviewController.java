package com.yourteamname.aipoweredcodereview.controller;

import com.yourteamname.aipoweredcodereview.service.CodeReviewService;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class CodeReviewController {
    @FXML
    private TextArea codeTextArea;
    @FXML
    private TextArea resultTextArea;

    private final CodeReviewService codeReviewService;

    public CodeReviewController() {
        this.codeReviewService = new CodeReviewService();
    }

    @FXML
    protected void onAnalyzeButtonClick() {
        String code = codeTextArea.getText();
        if (code == null || code.trim().isEmpty()) {
            resultTextArea.setText("Please enter some code to analyze.");
            return;
        }

        String result = codeReviewService.reviewCode(code);
        resultTextArea.setText(result);
    }
}