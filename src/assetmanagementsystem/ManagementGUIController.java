
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



}
