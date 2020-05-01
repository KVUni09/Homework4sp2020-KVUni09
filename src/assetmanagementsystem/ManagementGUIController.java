
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

    private void updateNumInCategoriesLabels(){
        int numComputers=0;
        int numPrinters=0;
        int numAuidoVideo=0;
        for (TechnologyAsset asset: assetsObservableList) {
            if (asset.getType()==TechnologyType.COMPUTER) {
                numComputers+=1;
            } else if (asset.getType()==TechnologyType.PRINTER) {
                numPrinters+=1;
            } else if (asset.getType()==TechnologyType.AUDIO_OR_VIDEO) {
                numAuidoVideo+=1;
            }
        }
        numComputerAsstsLabel.setText("Number of Computer Assets "+numComputers);
        numPrinterAssetsLabel.setText("Number of Printer Assets "+numPrinters);
        numAudioVideoAssetsLabel.setText("Number of Audio/Video Assets "+numAuidoVideo);
    }

    @FXML
    private void deleteAsset(ActionEvent event) {
        //if selected asset is not null, delete it
        if (selectedAsset != null) {
            String newOutput="";
            if (selectedAsset instanceof Computer) {
                //do nothing
            }
            else if (selectedAsset instanceof Printer) {
                Printer printer=(Printer)selectedAsset;
                printer.giveBackIPAddress();
            }
            else if (selectedAsset instanceof Projector) {
                Projector projector=(Projector)selectedAsset;
                projector.giveBackIPAddress();
            }
            outputTextArea.setText(outputTextArea.getText()+newOutput+"\n");
            assetsObservableList.remove(selectedAsset);
        }
    }

    @FXML
    private void clearAddPane(ActionEvent event) {
        addIDTextField.setText("");
        addNameTextField.setText("");
        locationTextFiield.setText("");
        usedByTextField.setText("");
    }

    @FXML
    private void addAsset(ActionEvent event) {
        if (assetTypeChoiceBox.selectionModelProperty().getValue().getSelectedItem() != null
                && assetStateChoiceBox.selectionModelProperty().getValue().getSelectedItem() != null
                && !addIDTextField.getText().equals("")
                && !addNameTextField.getText().equals("")
                && !locationTextFiield.getText().equals("")
                && !usedByTextField.getText().equals("")) {
            TechnologyType type = TechnologyType.UNDEFINED;
            switch (assetTypeChoiceBox.selectionModelProperty().getValue().getSelectedItem()) {
                case "Computer":
                    type = TechnologyType.COMPUTER;
                    break;
                case "Printer":
                    type = TechnologyType.PRINTER;
                    break;
                case "Audio/Video":
                    type = TechnologyType.AUDIO_OR_VIDEO;
                    break;
                default:
                    type = TechnologyType.UNDEFINED;
            }
            AssetState state = AssetState.UNKNOWN;
            switch (assetStateChoiceBox.selectionModelProperty().getValue().getSelectedItem()) {
                case "in use":
                    state = AssetState.IN_USE;
                    break;
                case "needs repair":
                    state = AssetState.NEEDS_REPAIR;
                    break;
                case "in storage":
                    state = AssetState.IN_STORAGE;
                    break;
                default:
                    state = AssetState.UNKNOWN;
            }
            try {
                //create subclass of TechnologyAsset based off of type enum
                TechnologyAsset newAsset = TechnologyAsset.createTechnologyAsset(addIDTextField.getText(), addNameTextField.getText(), type, locationTextFiield.getText(), usedByTextField.getText(), state);
                assetsObservableList.add(newAsset);
                Alert alert = new Alert(AlertType.INFORMATION, "Added new asset called " + newAsset.getName() + ".", ButtonType.OK);
                alert.show();
            } catch (Exception e) {
                System.out.println("Error trying to add technology asset: asset type may not be left blank.");
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING, "You must not leave any field blank.", ButtonType.OK);
            alert.show();
        }
    }

    @FXML
    private void saveAssets(ActionEvent event) {
        //save asets to file
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;
        try {
            fileWriter = new FileWriter(new File("assets.csv"));
            printWriter = new PrintWriter(fileWriter);
            printWriter.println("AssetIDNumber,AssetName,AssetType,AssetLocation,UsedBy,AssetState");
            for (TechnologyAsset asset : assetsObservableList) {
                printWriter.println(asset);
            }
        } catch (Exception e) {
            System.out.println("Error trying to write to file.");
        } finally {
            //close the file
            try {
                if (printWriter != null) {
                    printWriter.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (Exception e) {
                System.out.println("Error trying to close file.");
            }
        }
    }

    @FXML
    private void saveEdits(ActionEvent event) {
        //if slected asset is not null and fields are not blank, save edit from edit tab
        if (selectedAsset != null) {
            if (editAssetTypeChoiceBox.selectionModelProperty().getValue().getSelectedItem() != null
                    && editAssetStateChoiceBox.selectionModelProperty().getValue().getSelectedItem() != null
                    && !editIDTextField.getText().equals("")
                    && !editNameTextField.getText().equals("")
                    && !editLocationTextField.getText().equals("")
                    && !editUsedByTextField.getText().equals("")) {
                TechnologyType type = TechnologyType.UNDEFINED;
                switch (editAssetTypeChoiceBox.selectionModelProperty().getValue().getSelectedItem()) {
                    case "Computer":
                        type = TechnologyType.COMPUTER;
                        break;
                    case "Printer":
                        type = TechnologyType.PRINTER;
                        break;
                    case "Audio/Video":
                        type = TechnologyType.AUDIO_OR_VIDEO;
                        break;
                    default:
                        type = TechnologyType.UNDEFINED;
                }
                AssetState state = AssetState.UNKNOWN;
                switch (editAssetStateChoiceBox.selectionModelProperty().getValue().getSelectedItem()) {
                    case "in use":
                        state = AssetState.IN_USE;
                        break;
                    case "needs repair":
                        state = AssetState.NEEDS_REPAIR;
                        break;
                    case "in storage":
                        state = AssetState.IN_STORAGE;
                        break;
                    default:
                        state = AssetState.UNKNOWN;
                }
                try {
                    TechnologyAsset newAsset = TechnologyAsset.createTechnologyAsset(editIDTextField.getText(), editNameTextField.getText(), type, editLocationTextField.getText(), editUsedByTextField.getText(), state);
                    int index=assetsObservableList.indexOf(selectedAsset);
                    assetsObservableList.remove(selectedAsset);
                    assetsObservableList.add(index, newAsset);
                    Alert alert = new Alert(AlertType.INFORMATION, "Edited asset now called " + newAsset.getName() + ".", ButtonType.OK);
                    alert.show();
                } catch (Exception e) {
                    System.out.println("Error trying to add technology asset: invalid type.");
                }
            } else {
                Alert alert = new Alert(AlertType.WARNING, "No filed may be left blank.", ButtonType.OK);
                alert.show();
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING, "You must select an asset to edit.", ButtonType.OK);
            alert.show();
        }
    }

    @FXML
    private void useAsset(ActionEvent event) {
        if (selectedAsset!=null) {
            //call method based of off what subclass it is
            //subclass is determined with instanceof operator
            String newOutput="";
            if (selectedAsset instanceof Computer) {
                Computer computer=(Computer)selectedAsset;
                newOutput=computer.performComputations();
            }
            else if (selectedAsset instanceof Printer) {
                Printer printer=(Printer)selectedAsset;
                newOutput=printer.print();
            }
            else if (selectedAsset instanceof Projector) {
                Projector projector=(Projector)selectedAsset;
                newOutput=projector.project();
            }
            outputTextArea.setText(outputTextArea.getText()+newOutput+"\n");
        } else {
            Alert alert = new Alert(AlertType.WARNING, "You must select an asset to use.", ButtonType.OK);
            alert.show();
        }
    }

}
