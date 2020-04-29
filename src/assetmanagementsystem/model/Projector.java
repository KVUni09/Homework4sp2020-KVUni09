/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetmanagementsystem.model;

/**
 *
 * @author kyle
 */
public class Projector extends TechnologyAsset {
    
    public Projector(String idNumber, String name, String location, String usedBy, AssetState assetState){
        super.type=TechnologyType.AUDIO_OR_VIDEO;
        super.id=idNumber;
        super.name=name;
        super.type=type;
        super.location=location;
        super.usedBy=usedBy;
        super.assetState=assetState;
    }

    @Override
    public String configureIPAddress() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String performMaintenance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String project(){
        return "They're really just dots on the wall.";
    }
    
}
