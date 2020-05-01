
package assetmanagementsystem.model;

/**
 *
 * @author Kiel Roi Velasco
 */
public class Printer extends TechnologyAsset {

    public Printer(String idNumber, String name, String location, String usedBy, AssetState assetState){
        super.type=TechnologyType.PRINTER;
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
        return "Updating driver software and cleaning or replacing cartridges.";
    }

    public String print(){
        return "Look at me!  100 pages per minute";
    }
    
}
