package com.yourteamname.aipoweredcodereview.ui;

import com.yourteamname.aipoweredcodereview.service.CodeReviewService;

import javafx.application.Application;
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
        TextArea codeInputArea = new TextArea();
        codeInputArea.setPromptText("Enter your Java code here...");

        Button submitButton = new Button("Submit");
        TextArea responseArea = new TextArea();
        responseArea.setEditable(false);

        submitButton.setOnAction(e -> {
            String code = codeInputArea.getText();
            String response = CodeReviewService.sendCodeForAnalysis(code);
            responseArea.setText(response);
        });

        VBox layout = new VBox(10, titleLabel, codeInputArea, submitButton, responseArea);
        Scene scene = new Scene(layout, 600, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
