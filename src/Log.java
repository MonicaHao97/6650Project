import java.text.SimpleDateFormat;

public class Log {

    //Format time stamp in form of yyyy-MM-dd HH:mm:ss.SS
    public String timeStamp(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
        return formatter.format(time);
    }

    //Periodically shut down a server for 200 milliseconds
    public void errServerDown(int sv) {
        String res = timeStamp(System.currentTimeMillis()) +
                "ERR-FALT-TOLERANT-TEST: Server [" + sv+ "] has shut down for 200 milliseconds.";
        System.out.println("\n"+res);
    }

    //Return the Ack for clients' sent requests
    public void ackRequestSent(String req) {
        String res = timeStamp(System.currentTimeMillis()) +
                ">>> INFO-Paxos: Client sends the request: [" + req+ "] to server.";
        System.out.println("\n"+res);
    }

    //Response for consensus based on majority
    public void ackRequestReceived(String stage, int count){

        String res = timeStamp(System.currentTimeMillis()) +
                ">>> INFO-Paxos: ["+ count +"] servers positively replied for the [" + stage + "] stage.";
        System.out.println(res);
    }


    //No consensus based on majority
    public String errResponse(int count) {
        return timeStamp(System.currentTimeMillis()) +
                ">>> INFO-Paxos: [" + count+ "] servers has positively replied and not reached consensus.";
    }

    //Paxos log
    public void paxosLog(String msg){
        System.out.println(timeStamp(System.currentTimeMillis()) +
                ">>> INFO-Paxos: " + msg);
    }

    //Return the Ack for a successful PUT operation
    public String ackPut(int key, int val) {
        return timeStamp(System.currentTimeMillis()) +
                ">>> INFO-Paxos: Server stored the key-value pair: (Key: " + key + ", Value: " + val + ").";
    }

    //Return the error message for failed PUT operation: key already exists
    public String errPut(int key, int val) {
        return timeStamp(System.currentTimeMillis()) +
                ">>> INFO-Paxos: The Key: " + key + " already exists. " +
                "Server failed to store the key-value pair: (Key: " + key + ", Value: " + val + ").";
    }

    //Return the Ack for a successful GET operation
    public String ackGet(int key, int val) {
        return timeStamp(System.currentTimeMillis()) +
                ">>> INFO-Paxos: The value of given Key: " + key + " is: " + val + ".";
    }

    //Return the error message for failed GET operation: key does not exist
    public String errGet() {
        return timeStamp(System.currentTimeMillis()) +
                ">>> INFO-Paxos: The Key: \" + key + \" does not exist.";
    }

    //Return the Ack for a successful DELETE operation
    public String ackDel(int key, int val) {
        return timeStamp(System.currentTimeMillis()) +
                ">>> INFO-Paxos: The key-value pair: (Key: \" + key + \", Value: \" + val + \") has been removed.";
    }

    //Return the error message for failed DELETE operation: key does not exist
    public String errDel(int key) {
        return timeStamp(System.currentTimeMillis()) +
                ">>> INFO-Paxos: The Key: \" + key + \" does not exist.";
    }


    //Return the error message for invalid request input
    public void illegalRequestTypeException() {
        System.out.println(System.currentTimeMillis() +
                ">>> Error: Received invalid request from Client. Please check and try again.");
    }


    //Return the error message for connection exception
    public void connectionException() {
        System.out.println(
                timeStamp(System.currentTimeMillis()) +
                        ">>> Error: Connection exception or RMI error. Please check and try again.");
    }

    public void usage(){
        System.out.println(
                ">>> Expected Input Format: <Operation> <Key>" +
                        "\n>>> Example:<GET/PUT/DELETE> <testKey>" +
                        "\n>>> Please Enter Your Input Here:"
        );
    }

}
