import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class Server2 extends ServiceServer {

    public static void main(String args[]) throws Exception{

        Log log = new Log();

        try {
            Server2 server = new Server2(2);

            Service stub = (Service)
                    UnicastRemoteObject.exportObject(server, 0);
            Registry registry = LocateRegistry.createRegistry(Configure.SERVER2_PORT_NO);
            registry.bind(Configure.SERVER2, stub);

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println("Server2 started running");
            System.out.println(log.timeStamp(System.currentTimeMillis()));
        } catch (Exception e) {
            log.connectionException();
        }

    }

    public Server2(int serverNumber) throws RemoteException {
        super(serverNumber);
    }


}