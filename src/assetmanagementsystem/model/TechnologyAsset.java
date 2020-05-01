
package assetmanagementsystem.model;

/**
 *
 * @author Kiel Roi Velasco
 */
public abstract class TechnologyAsset implements IPAddressable, TechnologyMaintenance {

    protected String id;
    protected String name;
    protected TechnologyType type;
    protected String location;
    protected String usedBy;
    protected AssetState assetState;
    protected boolean hasIPAddress;
    protected String IPAddress;

    //create different subtype based on enum type and return it
    public static TechnologyAsset createTechnologyAsset(String id, String name, TechnologyType type, String location, String usedBy, AssetState assetState) throws Exception {
        switch (type) {
            case COMPUTER: return new Computer(id, name, location, usedBy, assetState);
            case AUDIO_OR_VIDEO: return new Projector(id, name, location, usedBy, assetState);
            case PRINTER: return new Printer(id, name, location, usedBy, assetState);
            default: throw new Exception();
        }
    }

    @Override
    public String toString(){
        return id+","+name+","+type.getName()+","+location+","+usedBy+","+assetState.getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TechnologyType getType() {
        return type;
    }

    public void setType(TechnologyType type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUsedBy() {
        return usedBy;
    }

    public void setUsedBy(String usedBy) {
        this.usedBy = usedBy;
    }

    public AssetState getAssetState() {
        return assetState;
    }

    public void setAssetState(AssetState assetState) {
        this.assetState = assetState;
    }
    
}
