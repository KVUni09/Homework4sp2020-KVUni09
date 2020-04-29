/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetmanagementsystem;

import assetmanagementsystem.model.AssetState;
import assetmanagementsystem.model.TechnologyAsset;
import assetmanagementsystem.model.TechnologyType;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author kyle
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
    
    ArrayList<TechnologyAsset> assets=new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            fileReader = new FileReader(new File("assets.csv"));
            bufferedReader = new BufferedReader(fileReader);
            String line=bufferedReader.readLine();
            line=bufferedReader.readLine();
            while(line!=null) {
                System.out.println(line);
                String[] assetStrings=line.split(",");
                TechnologyType type=TechnologyType.UNDEFINED;
                if (assetStrings[2].equals("Computer")) {
                    type=TechnologyType.COMPUTER;
                } else if (assetStrings[2].equals("Printer")) {
                    type=TechnologyType.PRINTER;
                } else if (assetStrings[2].equals("Audio/video")) {
                    type=TechnologyType.AUDIO_OR_VIDEO;
                }
                AssetState state=AssetState.UNKNOWN;
                if (assetStrings[5].equals("in use")) {
                    state=AssetState.IN_USE;
                } else if (assetStrings[5].equals("needs repair")) {
                    state=AssetState.NEEDS_REPAIR;
                } else if (assetStrings[5].equals("in storage")) {
                    state=AssetState.IN_STORAGE;
                }
                TechnologyAsset asset=TechnologyAsset.createTechnologyAsset(
                        assetStrings[0], assetStrings[1], type, assetStrings[3], assetStrings[4], state);
                assets.add(asset);
                line=bufferedReader.readLine();
            }
        } catch (Exception e) {
            System.out.println("Error trying work with file.");
        } finally {
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
    }

}
