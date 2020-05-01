
package assetmanagementsystem;

import assetmanagementsystem.model.AssetState;
import assetmanagementsystem.model.Computer;
import assetmanagementsystem.model.Printer;
import assetmanagementsystem.model.Projector;
import assetmanagementsystem.model.TechnologyAsset;
import assetmanagementsystem.model.TechnologyType;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Kiel Roi Velasco
 */
public class ManagementGUIController implements Initializable {

    @FXML
    private TableView<TechnologyAsset> assetTableView;
    @FXML
    private TableColumn<TechnologyAsset, String> idNumberTableColumn;
    @FXML
    private TableColumn<TechnologyAsset, String> assetNameTableColumn;
    @FXML
    private TableColumn<TechnologyAsset, String> assetTypeTableColumn;
    @FXML
    private TableColumn<TechnologyAsset, String> assetLocationTableColumn;
    @FXML
    private TableColumn<TechnologyAsset, String> usedByTableColumn;
    @FXML
    private TableColumn<TechnologyAsset, String> assetStateTableColumn;
    @FXML
    private Label numComputerAsstsLabel;
    @FXML
    private Label numPrinterAssetsLabel;
    @FXML
    private Label numAudioVideoAssetsLabel;
    @FXML
    private Label selectedItemLabel;
    @FXML
    private Button performMaintenanceButton;

    BufferedReader bufferedReader = null;
    FileReader fileReader = null;
    ObservableList<TechnologyAsset> assetsObservableList = FXCollections.observableArrayList();
    TechnologyAsset selectedAsset = null;
    @FXML
    private Button configureIPAddressButton;
    @FXML
    private TextArea outputTextArea;
    @FXML
    private Button useAssetButton;
    @FXML
    private TextField addIDTextField;
    @FXML
    private TextField addNameTextField;
    @FXML
    private TextField locationTextFiield;
    @FXML
    private TextField usedByTextField;
    @FXML
    private ChoiceBox<String> assetStateChoiceBox;
    @FXML
    private ChoiceBox<String> assetTypeChoiceBox;
    @FXML
    private Label editSlectedAssetLabel;
    @FXML
    private ChoiceBox<String> editAssetStateChoiceBox;
    @FXML
    private TextField editUsedByTextField;
    @FXML
    private TextField editLocationTextField;
    @FXML
    private ChoiceBox<String> editAssetTypeChoiceBox;
    @FXML
    private TextField editNameTextField;
    @FXML
    private TextField editIDTextField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //set items for table view to be an observable list of TechnologyAssets
        assetTableView.setItems(assetsObservableList);
        //try to read in all the dta from the file and instantiate
        //TechnologyAsset subclasses
        try {
            fileReader = new FileReader(new File("assets.csv"));
            bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            line = bufferedReader.readLine();
            while (line != null && line.trim() != "") {
                System.out.println(line);
                String[] assetStrings = line.split(",");
                TechnologyType type = TechnologyType.UNDEFINED;
                if (assetStrings[2].equals("Computer")) {
                    type = TechnologyType.COMPUTER;
                } else if (assetStrings[2].equals("Printer")) {
                    type = TechnologyType.PRINTER;
                } else if (assetStrings[2].equals("Audio/video")) {
                    type = TechnologyType.AUDIO_OR_VIDEO;
                }
                AssetState state = AssetState.UNKNOWN;
                if (assetStrings[5].equals("in use")) {
                    state = AssetState.IN_USE;
                } else if (assetStrings[5].equals("needs repair")) {
                    state = AssetState.NEEDS_REPAIR;
                } else if (assetStrings[5].equals("in storage")) {
                    state = AssetState.IN_STORAGE;
                }
                TechnologyAsset asset = TechnologyAsset.createTechnologyAsset(
                        assetStrings[0], assetStrings[1], type, assetStrings[3], assetStrings[4], state);
                assetsObservableList.add(asset);
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            System.out.println("Error trying to read from file.");
        } finally {
            //close the file
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (Exception e) {
                System.out.println("Error trying to close file.");
            }
        }
        //tell inital numbers for categories in labels
        updateNumInCategoriesLabels();
        //set fatories for how to use data from onbservable
        //list for each column in a row
        idNumberTableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(
                        cellData.getValue().getId()));

        assetNameTableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(
                        cellData.getValue().getName()));

        assetTypeTableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(
                        cellData.getValue().getType().getName()));
        assetLocationTableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(
                        cellData.getValue().getLocation()));
        usedByTableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(
                        cellData.getValue().getUsedBy()));
        assetStateTableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(
                        cellData.getValue().getAssetState().getName()));
        assetTableView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        selectedAsset = newSelection;
                        selectedItemLabel.setText("Selected Asset: " + selectedAsset.getName());
                        this.editSlectedAssetLabel.setText("Selected Asset: " + selectedAsset.getName());
                    } else {
                        selectedAsset=null;
                        selectedItemLabel.setText("Selected Asset: None Selected");
                        editSlectedAssetLabel.setText("Selected Asset: None Selected");
                    }
                });
        //set-up add and edit tab choice boc choices
        assetTypeChoiceBox.getItems().addAll("Computer", "Printer", "Audio/video");
        assetStateChoiceBox.getItems().addAll("in use", "needs repair", "in storage");
        editAssetTypeChoiceBox.getItems().addAll("Computer", "Printer", "Audio/video");
        editAssetStateChoiceBox.getItems().addAll("in use", "needs repair", "in storage");
        //if liost changes update category count in labels in case they changed
        assetsObservableList.addListener(new ListChangeListener<TechnologyAsset>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends TechnologyAsset> c) {
                updateNumInCategoriesLabels();
            }



        });
    }

}
