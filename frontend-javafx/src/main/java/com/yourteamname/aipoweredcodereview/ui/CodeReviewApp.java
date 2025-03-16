package com.yourteamname.aipoweredcodereview.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CodeReviewApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("AI Code Review");

        Label titleLabel = new Label("AI-Powered Code Review Tool");

        TextArea codeInput = new TextArea();
        codeInput.setPromptText("Enter Java code here...");
        codeInput.setPrefHeight(200);

        Button analyzeButton = new Button("Analyze Code");

        TextArea resultOutput = new TextArea();
        resultOutput.setPromptText("AI analysis results will appear here...");
        resultOutput.setEditable(false);
        resultOutput.setPrefHeight(150);

        analyzeButton.setOnAction(event -> {
            String code = codeInput.getText();
            if (!code.isEmpty()) {
                String analysisResult = analyzeCode(code);
                resultOutput.setText(analysisResult);
            } else {
                resultOutput.setText("Please enter code for analysis.");
            }
        });

        VBox layout = new VBox(10, titleLabel, codeInput, analyzeButton, resultOutput);
        layout.setPadding(new Insets(15));

        Scene scene = new Scene(layout, 500, 400);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String analyzeCode(String code) {
        // Placeholder for AI backend logic
        return "Code Analysis: \n- No syntax errors detected.\n- Optimize loop usage.";
    }

    public static void main(String[] args) {
        launch(args);
    }
}
