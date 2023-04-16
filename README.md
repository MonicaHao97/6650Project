# Project 4 PAXOS Key-Value Store README
****
### **Structure of the Project**
1. /src: contains all code files for Client, Server, and Service
2. /out: contains all execution files for /artifacts: JAR files and /production: compiled java class
### **What's included:**
1. Client.java & Client.class
2. Server1/2/3/4/5.java & Server1/2/3/4/5.class
3. ServerStore/KeyValueStore/ServiceStore.java & ServerStore/KeyValueStore/ServiceStore.class 
4. Service/Configure.java & Service/Configure.class 
5. Acceptor/Learner/Proposer.java & Acceptor/Learner/Proposer.class 
6. Log.java & Log.class 
7. Server1/2/3/4/5/Client.jar 
8. README.md file & server.properties
### **How to Run PAXOS KVS (MacOS) Using JAR**
1. Open terminal
2. Go to the /out/artifacts directory where Server1/2/3/4/5.jar and Client.jar locate at
3. Open 6 separate tabs for each server and client
4. Run `java -jar Server1/2/3/4/5.jar` in the first 5 tabs and `java -jar Client.jar Server3` in the last tab.
5. Messages of *Server [number] started running* should appear in terminal
6. After client runs, there should be 25 pre-populated commands in each tab.
7. Then you could run operations in client's tab for GET, PUT, and DELETE key-value pairs 
### **How to Run the Code For PAXOS KVS (MacOS) From Start:**
1. Open terminal
2. Go to the directory where above files are: the client, server, and service packages
3. Compile all the files using `javac [File Name]`. Please compile all the files within one command in each package
6. If above step fails, got to /out directory and use pre-compiled java class files
7. Open another tab under the same directory.
8. In the newly opened tab, run `java Server1/2/3/4/5` message *Server [number] started running* should appear
10. Switch to the other tab, run `java Client Server3` 25 pre-populated commands will print.
12. Stay in the client tab, after the *Please Enter Here:* enter the operation and key-value pair
13. Hit enter then you should see the result and both client and server should print messages. Client will continue running 
14. Run client as many times as you want
15. Press Control-C anytime at the server tab when done with multi-thread server




