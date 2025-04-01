package com.yourteamname.aipoweredcodereview.ui;

import com.yourteamname.aipoweredcodereview.service.CodeReviewService;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
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
        Label codeInputLabel = new Label("Enter Code:");
        codeInputLabel.getStyleClass().add("label");

        codeInput = new TextArea();
        codeInput.setPromptText("Enter your code here...");
        codeInput.getStyleClass().add("text-area");

        Button analyzeButton = new Button("Analyze Code");
        analyzeButton.setOnAction(e -> analyzeCode());
        analyzeButton.getStyleClass().add("button");

        Label resultOutputLabel = new Label("Analysis Result:");
        resultOutputLabel.getStyleClass().add("label");

        resultOutput = new TextArea();
        resultOutput.setEditable(false);
        resultOutput.getStyleClass().add("text-area");

        // Layout for code input
        VBox codeInputLayout = new VBox(10, codeInputLabel, codeInput, analyzeButton);
        codeInputLayout.setPadding(new Insets(10));
        codeInputLayout.setSpacing(10);
        codeInputLayout.getStyleClass().add("layout");
        VBox.setVgrow(codeInput, Priority.ALWAYS);

        // Layout for result output
        VBox resultOutputLayout = new VBox(10, resultOutputLabel, resultOutput);
        resultOutputLayout.setPadding(new Insets(10));
        resultOutputLayout.setSpacing(10);
        resultOutputLayout.getStyleClass().add("layout");
        VBox.setVgrow(resultOutput, Priority.ALWAYS);

        // Main layout
        HBox mainLayout = new HBox(20, codeInputLayout, resultOutputLayout);
        mainLayout.setPadding(new Insets(10));
        mainLayout.setSpacing(20);
        mainLayout.getStyleClass().add("layout");
        HBox.setHgrow(codeInputLayout, Priority.ALWAYS);
        HBox.setHgrow(resultOutputLayout, Priority.ALWAYS);

        // Scene
        Scene scene = new Scene(mainLayout, 1200, 600);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

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