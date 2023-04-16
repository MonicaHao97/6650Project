import java.net.SocketTimeoutException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Map;


public class Proposer extends KeyValueStore implements Runnable{

    private static int proposalId;
    private int value;


    public Proposer(){
        super();
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void start(){
        proposalId = 0;
    }

    public synchronized String propose(int key, int reqType){
        String response = "";
        setValue(key);
        Map<String, String> serverMap = ServerStore.getServerMap();
        Registry registry;
        int count = 0;
        proposalId ++;

        try{
            for(Map.Entry<String, String> entry : serverMap.entrySet()){
                try{
                    registry = LocateRegistry.getRegistry(entry.getValue(),
                            ServerStore.getPortNumber(entry.getKey()));
                    Service stub = (Service)
                            registry.lookup(entry.getKey());
                    if(stub.prepare(proposalId, key, reqType)){
                        count ++;
                    }
                }catch(SocketTimeoutException | RemoteException e ){
                    continue;
                }
            }

            if(count > (Configure.NUMBER_OF_SERVERS / 2)){
                log.ackRequestReceived("PREPARE",count);
                count = 0;
                for(Map.Entry<String, String> entry : serverMap.entrySet()){
                    try{
                        registry = LocateRegistry.getRegistry(entry.getValue(),
                                ServerStore.getPortNumber(entry.getKey()));
                        Service stub = (Service)
                                registry.lookup(entry.getKey());
                        if(stub.accept(proposalId, key, reqType)){
                            count ++;
                        }
                    }catch(SocketTimeoutException | RemoteException e){
                        continue;
                    }
                }
            } else {
                response = log.errResponse(count);
                log.paxosLog(response);
                return response;
            }

            if(count > (Configure.NUMBER_OF_SERVERS / 2)){
                log.ackRequestReceived("ACCEPT",count);
                for(Map.Entry<String, String> entry : serverMap.entrySet()){
                    try{
                        registry = LocateRegistry.getRegistry(entry.getValue(),
                                ServerStore.getPortNumber(entry.getKey()));
                        Service stub = (Service)
                                registry.lookup(entry.getKey());
                        response = stub.commit(key, reqType);
                    }catch(SocketTimeoutException | RemoteException e){
                        continue;
                    }
                }
            } else {
                response = log.errResponse(count);
                log.paxosLog(response);
                return response;
            }
        }
        catch(NotBoundException e){
            log.connectionException();
        }
        return response;
    }

    @Override
    public void run() {}
}

