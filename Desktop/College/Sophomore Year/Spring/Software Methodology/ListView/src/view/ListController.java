/*package view;

public class ListController {
}
*/


package view;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
public class ListController {
    @FXML
    ListView<String> listView;
    private ObservableList<String> obsList;
    public void start() {
        // create an ObservableList
        // from an ArrayList
        obsList = FXCollections.observableArrayList(
                "Giants","Patriots","Jaguars");
        listView.setItems(obsList);
    }
}