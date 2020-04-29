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
public class Computer extends TechnologyAsset {
    
    public Computer(String idNumber, String name, String location, String usedBy, AssetState assetState){
        super.type=TechnologyType.COMPUTER;
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
    
    public String performComputations(){
        return "Happily computing with those 1's and 0's";
    }
    
}
