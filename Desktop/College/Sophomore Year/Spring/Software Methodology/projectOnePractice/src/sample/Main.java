package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
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
        GridPane root = makeGridPane();
       // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Song Library");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static GridPane makeGridPane(){
        GridPane grid = new GridPane();

        Text insert = new Text("Please add details of your song");
        Text song = new Text("Add Song");
        Text artist = new Text("Add Artist");
        Text album = new Text("Add Album");

        TextField s = new TextField();
        s.setPromptText("ex: 22");
        TextField a = new TextField();
        a.setPromptText("ex: Taylor Swift");
        TextField al = new TextField();
        al.setPromptText("ex: Red");

        Button addSong = new Button("Add Song to Library");


        grid.add(insert,1,0);

        grid.add(song, 0,1);
        grid.add(s, 0,2);

        grid.add(artist,1,1);
        grid.add(a,1,2);

        grid.add(album,2,1);
        grid.add(al, 2,2);

        grid.add(addSong, 1,3); //add button


        addSong.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Text message = new Text("You added " + s.getText() + " by " + a.getText() + " from the album " + al.getText());
                //grid.add(message, 1,4);
                //addSong.setText(String.format("You added song % by % from album %", s.getText(),a.getText(),al.getText()));
                addSong.setText(String.format("Added Song"));
                System.out.println("Artist: "+a.getText() + ", Song: "+s.getText()+ ", Album: "+al.getText());
            }
        });

        return grid;

    }





    public static void main(String[] args) {
        launch(args);
    }
}
