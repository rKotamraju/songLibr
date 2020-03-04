//Rachana K and Sujit M

package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SongLib extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));

        Parent root = loader.load();

        Controller controller = loader.getController();

        primaryStage.setOnCloseRequest(e-> closeProgram(controller));
        primaryStage.setResizable(false);

        primaryStage.setTitle("Song Library");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void closeProgram(Controller controller){
        //write Song Objects

        //String[] components;
        FileWriter fWriter = null;
        BufferedWriter writer = null;

            try{
                fWriter = new FileWriter("savedSongs.txt");
                writer = new BufferedWriter(fWriter);
                for(SongDetail i : controller.songsObservableList) {
                    String addToText = i.returnStringID();
                    writer.write(addToText);
                    writer.newLine();
                }
                writer.close();
            } catch (Exception e) {
                System.out.println("Error");
            }
            /*

            SongDetail prevSong;
        //inside the file looping
            components = i.returnStringID().split(",");
            prevSong = new Song(components[1], components[2], components[3], components[0]);



//            System.out.println(components.length);
//            for( String s : components){
//                System.out.print(s+" ");
//            }
//            System.out.println();
*/

    }


}
