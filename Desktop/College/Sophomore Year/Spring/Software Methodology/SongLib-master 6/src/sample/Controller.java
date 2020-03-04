//Rachana Kotamraju and Sujit Molleti

package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.Scanner;

import static javafx.collections.FXCollections.observableArrayList;


public class Controller implements Initializable {

//TEXTFIELDs
    @FXML
    private TextField SongTextField;
    @FXML
    private TextField AlbumTextField;
    @FXML
    private TextField ArtistTextField;
    @FXML
    private TextField YearTextField;

//BUTTONs
    @FXML
    private Button AddButton;
    @FXML
    private Button DltButton;
    @FXML
    private Button EditButton;
    @FXML
    private Button ClearButton;
    @FXML
    private Button CloseButton;

//LABELS
    @FXML
    private Label SongLabel;
    @FXML
    private Label ArtistLabel;
    @FXML
    private Label AlbumLabel;
    @FXML
    private Label YearLabel;

    boolean editingMode = false;


//SONG LIST
    @FXML
    public ListView<SongDetail> SongListView;

    final ObservableList<String> songNamesList = observableArrayList();
    final public ObservableList<SongDetail> songsObservableList = observableArrayList();

    @FXML
    public void addButtonClicked(ActionEvent e){
        String song = SongTextField.getText().trim();
        String album = AlbumTextField.getText().trim();
        String artist = ArtistTextField.getText().trim();
        String year = YearTextField.getText().trim();

        if(year.length() != 0) { //if a year is inputted
            try {
                int x = Integer.parseInt(year);
                if (x < 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid year!", ButtonType.OK);
                    alert.showAndWait();
                    return;
                }

            } catch (NumberFormatException error) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid year!", ButtonType.OK);
                alert.showAndWait();
                return;
            }
        }

//CHECKING IF SONG OR ARTIST TEXTFIELDs are EMPTY
        if(song.length() == 0 || artist.length() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter both a song and an artist name!", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        SongDetail temp = new SongDetail(artist, album, year, song);

    //CHECKING FOR DUPLICATES USING SEQUENTIAL SEARCH
        if(editingMode == false) {
            for (SongDetail i : songsObservableList) {
                if (i.song.compareToIgnoreCase(temp.song) == 0) {
                    if (i.artist.compareToIgnoreCase(temp.artist) == 0) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Sorry, this song already exists and cannot be added!", ButtonType.OK);
                        alert.showAndWait();
                        return;
                    }
                }
            }
        }

        //All our cases have passed, now we can remove the old song, and replace it
        if(editingMode == true){
            deleteButtonClicked();
        }

//USING SEQUENTIAL SEARCH TO INSERT ALPHABETICALLY
        int insertPosition = 0;
        for( SongDetail i : songsObservableList){
            if(temp.song.compareToIgnoreCase(i.song) < 0){
                break;
            }

        //IF SONG NAMES ARE THE SAME, COMPARE ARTIST NAMES
            if(temp.song.compareToIgnoreCase(i.song) == 0){
                if(temp.artist.compareToIgnoreCase(i.artist) < 0){
                    break;
                }
            }
            insertPosition++;
        }

        //ENABLING LISTVIEW
        SongListView.setDisable(true);
        songsObservableList.add(insertPosition , temp);


        SongListView.getSelectionModel().select(insertPosition);
        SongDetail selectedSong = SongListView.getSelectionModel().getSelectedItem();
        displaySongDetails(selectedSong);

        SongTextField.clear();
        ArtistTextField.clear();
        AlbumTextField.clear();
        YearTextField.clear();
        ClearButton.setDisable(true);

//Reseting from Editing Mode
        AddButton.setText("Add");
        ClearButton.setText("Clear");
        SongListView.setDisable(false);
        editingMode = false;
    }

    @FXML
    private void clearButtonClicked(ActionEvent e){
        SongTextField.clear();
        ArtistTextField.clear();
        AlbumTextField.clear();
        YearTextField.clear();


//Reseting from Editing Mode
        if(editingMode == true) {
            AddButton.setText("Add");
            ClearButton.setText("Clear");
            SongListView.setDisable(false);
            editingMode = false;
        }
    }

    @FXML
    private void deleteButtonClicked(){
        int selectedItem = SongListView.getSelectionModel().getSelectedIndex();
        ButtonType userChoice = ButtonType.NO;

        if(editingMode == false) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want make this change?", ButtonType.CANCEL, ButtonType.YES);
            alert.showAndWait();
            userChoice = alert.getResult();
        }

        if(userChoice == ButtonType.YES || editingMode == true) {
            songsObservableList.remove(selectedItem); //this is the only relevant line for editing mode
            if(songsObservableList.size() == 0) {
                SongLabel.setText("Song: ");
                ArtistLabel.setText("Artist: ");
                AlbumLabel.setText("Album: ");
                YearLabel.setText("Year: ");
                SongListView.setDisable(true);

                //DISABLING EDIT AND DELETE BUTTON BECAUSE ONCE YOU DELETE THERE IS NO SELECTED ITEM
                DltButton.setDisable(true);
                EditButton.setDisable(true);
            } else { // else statement is for if the size is not empty
                /*When editing, this code will be run, but it will also be overrided by the select in addButton method
                    which follows this code
                 */
                SongListView.getSelectionModel().select(selectedItem);

                //DISPLAYING NEXT SONG IN LIST
                SongDetail selectedSong = SongListView.getSelectionModel().getSelectedItem();
                displaySongDetails(selectedSong);
            }

        }

    }

    @FXML
    private void editButtonClicked(ActionEvent e){
        SongDetail selectedSong = SongListView.getSelectionModel().getSelectedItem();
        SongTextField.setText(selectedSong.song);
        ArtistTextField.setText(selectedSong.artist);
        AlbumTextField.setText(selectedSong.album);
        YearTextField.setText(selectedSong.year);

        AddButton.setText("Confirm");
        ClearButton.setText("Cancel");


        SongListView.setDisable(true);
        EditButton.setDisable(true);
        DltButton.setDisable(true);
        editingMode = true;

    }

    @FXML
    private void selectSongFromListView(){
        //System.out.println("Selected song from list view");
        SongDetail selectedSong = SongListView.getSelectionModel().getSelectedItem();
        displaySongDetails(selectedSong);

        //ABLING THE DELETE AND EDIT BUTTONS
        DltButton.setDisable(false);
        EditButton.setDisable(false);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AddButton.setDisable(true);
        DltButton.setDisable(true);
        EditButton.setDisable(true);
        ClearButton.setDisable(true);
        SongListView.setDisable(true);

        readd();
        SongListView.setItems(songsObservableList);
        if(!songsObservableList.isEmpty()) { //run if only the list is populated
            displaySongDetails(songsObservableList.get(0));
            SongListView.getSelectionModel().select(0);
        }
        //read file and add contents
        //readd contents
        //clear contents

        //ABLING THE ADD BUTTON BECAUSE WE ARE FOCUSING ON THE SONG TEXTFIELD
        SongTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                if(SongTextField.isFocused()){
                    AddButton.setDisable(false);
                    ClearButton.setDisable(false);
                }
            }
        });

        //FOCUSING ON THE LISTVIEW
        SongListView.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(SongListView.isFocused()){
                    //SETTING TEXT
                    SongDetail selectedSong = SongListView.getSelectionModel().getSelectedItem();
                    displaySongDetails(selectedSong);

                    //ABLING THE DELETE AND EDIT BUTTONS
                    DltButton.setDisable(false);
                    EditButton.setDisable(false);
                }
            }
        });



    }

    private void readd() {
        //System.out.println("Readding methods from file");
        try {
            //String fileLine;
            String[] components;
            Scanner scan = new Scanner(new File("savedSongs.txt"));
            while(scan.hasNext()){
               // System.out.println(scan.nextLine());
                //adding songs to observable list;
                SongDetail prevSong;
                //inside the file looping
                //components = i.returnStringID().split(",");
                components = scan.nextLine().split(",&");
                prevSong = new SongDetail(components[1], components[2], components[3], components[0]);
                songsObservableList.add(prevSong);
                SongListView.setDisable(false);

//            System.out.println(components.length);
//            for( String s : components){
//                System.out.print(s+" ");
//            }
//            System.out.println();

            }
        } catch (FileNotFoundException e) {
            return;
        }


    }

    public void displaySongDetails(SongDetail selectedSong){
        SongLabel.setText("Song: "+selectedSong.song);
        ArtistLabel.setText("Artist: "+selectedSong.artist);
        AlbumLabel.setText("Album: "+selectedSong.album);
        YearLabel.setText("Year: "+selectedSong.year);

    }
}
