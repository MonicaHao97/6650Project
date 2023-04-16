import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class Server1 extends ServiceServer {

    public static void main(String args[]) throws Exception{

        Log log = new Log();

        try {
            Server1 server = new Server1(1);

            Service stub = (Service)
                    UnicastRemoteObject.exportObject(server, 0);
            Registry registry = LocateRegistry.createRegistry(Configure.SERVER1_PORT_NO);
            registry.bind(Configure.SERVER1, stub);

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println("Server1 started running");
            System.out.println(log.timeStamp(System.currentTimeMillis()));
        } catch (Exception e) {
            log.connectionException();
        }

    }

    public Server1(int serverNumber) throws RemoteException {
        super(serverNumber);
    }


}