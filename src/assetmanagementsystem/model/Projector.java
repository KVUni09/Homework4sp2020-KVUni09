
package assetmanagementsystem.model;

/**
 *
 * @author Kiel Roi Velasco
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
        super.IPAddress=IPAddressConfigurer.getInstance().staticIPAddresses.remove(0);
        return "Your new static IP adress is "+super.IPAddress;
    }

    public void giveBackIPAddress(){
        IPAddressConfigurer.getInstance().staticIPAddresses.add(super.IPAddress);
        super.IPAddress="";
    }

    @Override
    public String performMaintenance() {
        return "Cleaning projector window and replacing projector lamp.";
    }

    public String project(){
        return "They're really just dots on the wall.";
    }
    
}
