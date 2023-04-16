import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Client {


    public static void main(String args[]) throws Exception{
        Log log = new Log();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("Client started running");
        System.out.println(log.timeStamp(System.currentTimeMillis()));

        try {
            Registry registry = LocateRegistry.getRegistry("LOCALHOST", ServerStore.getPortNumber(args[0]));
            Service stub = (Service) registry.lookup(args[0]);

            prePopulateData(stub);

            while(true) {

                log.usage();
                String input = takeInputFromTerminal();
                String[] formattedInput = input.split(" ");
                log.ackRequestSent(input);

                if(formattedInput[0].equalsIgnoreCase("PUT")) {
                    System.out.println(stub.put(Integer.valueOf(formattedInput[1])));
                } else if(formattedInput[0].equalsIgnoreCase("GET")) {
                    System.out.println(stub.get(Integer.valueOf(formattedInput[1])));
                } else if(formattedInput[0].equalsIgnoreCase("DELETE")) {
                    System.out.println(stub.delete(Integer.valueOf(formattedInput[1])));
                } else {
                    log.illegalRequestTypeException();
                }
            }
        }
        catch(RemoteException | NotBoundException e){
            log.connectionException();
        }
    }

    public static void prePopulateData(Service stub) throws Exception {
        Log log = new Log();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("Pre-Populate Operations: 10 PUTs, 10 GETs, 5 DELETEs");
        System.out.println(log.timeStamp(System.currentTimeMillis()));

        int[] testKey = new int[10];

        for(int i = 0; i < 10; i++){
            testKey[i] = (int) (Math.random() * 100);
            log.ackRequestSent("PUT " + testKey[i]);
            System.out.println(stub.put(testKey[i]));
        }

        for(int i = 0; i < 5; i++){
            log.ackRequestSent("DELETE " + testKey[i]);
            System.out.println(stub.delete(testKey[i]));
        }

        for(int i = 5; i < 10; i++){
            log.ackRequestSent("GET " + testKey[i]);
            System.out.println(stub.get(testKey[i]));
        }
    }

    public static String takeInputFromTerminal() throws IOException {
        BufferedReader stringIn = new BufferedReader (new InputStreamReader(System.in));
        return  stringIn.readLine();
    }



}