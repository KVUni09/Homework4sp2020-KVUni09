
package assetmanagementsystem.model;

/**
 *
 * @author Kiel Roi Velasco
 */
public enum TechnologyType {
    COMPUTER("Computer"), PRINTER("Printer"), 
    AUDIO_OR_VIDEO("Audio/video"), UNDEFINED("Undefined");
    private final String name;
    TechnologyType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
}
