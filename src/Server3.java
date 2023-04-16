import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class Server3 extends ServiceServer {

    public static void main(String args[]) throws Exception{

        Log log = new Log();

        try {
            Server3 server = new Server3(3);

            Service stub = (Service)
                    UnicastRemoteObject.exportObject(server, 0);
            Registry registry = LocateRegistry.createRegistry(Configure.SERVER3_PORT_NO);
            registry.bind(Configure.SERVER3, stub);

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println("Server3 started running");
            System.out.println(log.timeStamp(System.currentTimeMillis()));
        } catch (Exception e) {
            log.connectionException();
        }

    }

    public Server3(int serverNumber) throws RemoteException {
        super(serverNumber);
    }


}