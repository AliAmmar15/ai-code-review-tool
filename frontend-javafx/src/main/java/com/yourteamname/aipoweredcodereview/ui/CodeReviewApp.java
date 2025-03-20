package com.yourteamname.aipoweredcodereview.ui;

import com.yourteamname.aipoweredcodereview.service.CodeReviewService;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CodeReviewApp extends Application {
    private CodeReviewService codeReviewService;
    private TextArea codeInput;
    private TextArea resultOutput;

    @Override
    public void start(Stage primaryStage) {
        codeReviewService = new CodeReviewService();

        // Create UI components
        codeInput = new TextArea();
        codeInput.setPromptText("Enter your code here...");
        codeInput.setPrefRowCount(10);

        resultOutput = new TextArea();
        resultOutput.setEditable(false);
        resultOutput.setPrefRowCount(10);

        Button analyzeButton = new Button("Analyze Code");
        analyzeButton.setOnAction(e -> analyzeCode());

        // Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(codeInput, analyzeButton, resultOutput);

        // Scene
        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setTitle("Code Review Tool");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void analyzeCode() {
        String code = codeInput.getText();
        if (code == null || code.trim().isEmpty()) {
            resultOutput.setText("Please enter some code to analyze.");
            return;
        }

        String result = codeReviewService.reviewCode(code);
        resultOutput.setText(result);
    }

    public static void main(String[] args) {
        launch(args);
    }
}