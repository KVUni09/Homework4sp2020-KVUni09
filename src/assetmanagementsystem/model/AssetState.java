/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetmanagementsystem.model;

/**
 *
 * @author mattroberts
 */
public enum AssetState {
    IN_USE("in use"), NEEDS_REPAIR("needs repair"), 
    IN_STORAGE("in storage"), UNKNOWN("unknown");
    private final String name;
    AssetState(String name) {
        this.name = name;
    }
}
