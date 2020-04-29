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
public abstract class TechnologyAsset implements IPAddressable, TechnologyMaintenance {
    protected String id;
    protected String name;
    protected TechnologyType type;
    protected String location;
    protected String usedBy;
    protected AssetState assetState;
    
    public static TechnologyAsset createTechnologyAsset(String id, String name, TechnologyType type, String location, String usedBy, AssetState assetState) throws Exception {
        switch (type) {
            case COMPUTER: return new Computer(id, name, location, usedBy, assetState);
            case AUDIO_OR_VIDEO: return new Projector(id, name, location, usedBy, assetState);
            case PRINTER: return new Printer(id, name, location, usedBy, assetState);
            default: throw new Exception();
        }
    }
}
