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
public enum TechnologyType {
    COMPUTER("Computer"), PRINTER("Printer"), 
    AUDIO_OR_VIDEO("Audio/Video"), UNDEFINED("Undefined");
    private final String name;
    TechnologyType(String name) {
        this.name = name;
    }
}
