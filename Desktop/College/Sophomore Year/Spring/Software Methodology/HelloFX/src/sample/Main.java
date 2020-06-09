package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
       // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        GridPane root = makeGridPane();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Farenheit to Celsius Converter");
      // primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private static GridPane makeGridPane(){
        GridPane gridPane = new GridPane();
        Text fText = new Text("farenheit");
        Text cText = new Text("celsius");
        TextField f = new TextField();
        TextField c = new TextField();
        Button f2c = new Button(">>>");
        Button c2f = new Button("<<<");


        gridPane.add(c2f,1,1); //button
        gridPane.add(f2c,1,0); //button

        gridPane.add(fText,0,0);
        gridPane.add(cText,2,0);

        gridPane.add(f,0,1);
        gridPane.add(c,2,1);
        return gridPane;

    }


    public static void main(String[] args) {

        launch(args);
    }
}
