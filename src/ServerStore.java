import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class ServerStore {


    public static Map<String, String> getServerMap(){
        Map<String, String> propertyMap = new HashMap<String, String>();

        try{
            Properties properties = new Properties();
            InputStream in = ServerStore.class.getResourceAsStream("server.properties");
            properties.load(in);
            Enumeration<?> e = properties.propertyNames();
            while (e.hasMoreElements()) {
                String name = (String) e.nextElement();
                String value = properties.getProperty(name);
                propertyMap.put(name, value);
            }
        } catch(FileNotFoundException fnfe){
            System.out.println("Properties file not found" + fnfe);
        } catch(IOException ioe){
            System.out.println("IOException while reading the properties file" + ioe);
        }
        return propertyMap;
    }

    public static int getPortNumber(String value) {
        if(value.equals(Configure.SERVER1)){
            return Configure.SERVER1_PORT_NO;
        }
        if(value.equals(Configure.SERVER2)){
            return Configure.SERVER2_PORT_NO;
        }
        if(value.equals(Configure.SERVER3)){
            return Configure.SERVER3_PORT_NO;
        }
        if(value.equals(Configure.SERVER4)){
            return Configure.SERVER4_PORT_NO;
        }
        if(value.equals(Configure.SERVER5)){
            return Configure.SERVER5_PORT_NO;
        }
        return 0;
    }
}