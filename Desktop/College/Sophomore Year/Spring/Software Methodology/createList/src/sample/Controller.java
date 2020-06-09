package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class Controller {
  @FXML
 ListView<String> listView;
  private ObservableList<String> obsList;
  public void start(){
   //create an ObservableList
   //from an ArrayList
   obsList = FXCollections.observableArrayList("Rare by Selena Gomez", "Watermelon Sugar by Harry Styles", "Right Now by Nick Jonas", "Liar by Camilla Cabello", "Crashing by Illenium","Rewrite the Stars");
   listView.setItems(obsList);
  }
}