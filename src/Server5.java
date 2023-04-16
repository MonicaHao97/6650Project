import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class Server5 extends ServiceServer {

    public static void main(String args[]) throws Exception{

        Log log = new Log();

        try {
            Server5 server = new Server5(5);

            Service stub = (Service)
                    UnicastRemoteObject.exportObject(server, 0);
            Registry registry = LocateRegistry.createRegistry(Configure.SERVER5_PORT_NO);
            registry.bind(Configure.SERVER5, stub);

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println("Server5 started running");
            System.out.println(log.timeStamp(System.currentTimeMillis()));
        } catch (Exception e) {
            log.connectionException();
        }

    }

    public Server5(int serverNumber) throws RemoteException {
        super(serverNumber);
    }


}