module com.yourteamname.aipoweredcodereview {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.apache.httpcomponents.client5;

    opens com.yourteamname.aipoweredcodereview to javafx.fxml;
    exports com.yourteamname.aipoweredcodereview;
    exports com.yourteamname.aipoweredcodereview.controller;
    opens com.yourteamname.aipoweredcodereview.controller to javafx.fxml;
}
