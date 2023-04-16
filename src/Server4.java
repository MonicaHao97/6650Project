import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class Server4 extends ServiceServer {

    public static void main(String args[]) throws Exception{

        Log log = new Log();

        try {
            Server4 server = new Server4(4);

            Service stub = (Service)
                    UnicastRemoteObject.exportObject(server, 0);
            Registry registry = LocateRegistry.createRegistry(Configure.SERVER4_PORT_NO);
            registry.bind(Configure.SERVER4, stub);

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println("Server4 started running");
            System.out.println(log.timeStamp(System.currentTimeMillis()));
        } catch (Exception e) {
            log.connectionException();
        }

    }

    public Server4(int serverNumber) throws RemoteException {
        super(serverNumber);
    }


}