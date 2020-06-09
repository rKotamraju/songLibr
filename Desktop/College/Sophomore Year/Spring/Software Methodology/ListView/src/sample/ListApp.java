package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.ListController;

//package app;
public class ListApp extends Application {
    public void start(Stage primaryStage)
            throws Exception {
       // FXMLLoader loader = new FXMLLoader();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/List.fxml"));
        AnchorPane root = (AnchorPane)loader.load();
        ListController listController = loader.getController();
        listController.start();
        Scene scene = new Scene(root, 200, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}