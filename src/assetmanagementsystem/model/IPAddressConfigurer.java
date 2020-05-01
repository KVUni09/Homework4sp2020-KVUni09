
package assetmanagementsystem.model;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Kiel Roi Velasco
 */
public class IPAddressConfigurer {

    private static IPAddressConfigurer instance=null;

    public static IPAddressConfigurer getInstance(){
        if (instance==null){
            instance=new IPAddressConfigurer();
        }
        return instance;
    }

    static Random randomNumGenerator=new Random();

    ArrayList<String> staticIPAddresses=new ArrayList<>();

    public IPAddressConfigurer(){
        makePool();
    }

    //addresses in pool are unique
    private void makePool(){
        for (int i=0; i<100; i++) {
            System.out.println(i);
            boolean unique=false;
            //loop until a unique address is found
            while (!unique) {
                unique=addUniqueIPAddress();
            }

        }
    }

    public static String makeIPAddress(){
        return getIPComponent()+"."+getIPComponent()+"."+getIPComponent()+"."+getIPComponent();
    }

    //add unique address and return true.  Else, return false
    private boolean addUniqueIPAddress(){
        String newAddress=makeIPAddress();
        for (String string : staticIPAddresses) {
            if (newAddress.equals(string)){
                return false;
            }
        }
        staticIPAddresses.add(newAddress);
        return true;
    }

    private static String getIPComponent(){
        return String.valueOf(randomNumGenerator.nextInt(256));
    }

}
