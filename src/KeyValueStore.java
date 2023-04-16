import java.util.HashMap;


public class KeyValueStore {

    static HashMap<Integer, Integer> hashMap;
    Log log = new Log();

    public KeyValueStore(){
        hashMap = new HashMap<>();
    }

    private static int generateValue(int key){
        return key * 10;
    }

    public String put(int key) {
        String response = "";

        if(!hashMap.containsKey(key)){
            hashMap.put(key, generateValue(key));
            response = log.ackPut(key, generateValue(key));

        } else {
            response = log.errPut(key,generateValue(key));
        }
        System.out.println(response);
        return response;
    }

    public String get(int key) {
        String response = "";
        if(hashMap.containsKey(key)){
            int val = hashMap.get(key);
            response  = log.ackGet(key,val);
        } else {
            response = log.errGet();
        }
        System.out.println(response);
        return response;
    }

    public String delete(int key) {
        String response = "";
        if(hashMap.containsKey(key)){
            hashMap.remove(key,generateValue(key));
            response = log.ackDel(key, generateValue(key));
        }else {
            response = log.errDel(key);
        }

        System.out.println(response);
        return response;
    }

    public boolean checkReq(int key, int action){

        switch(action) {
            case 1:if(hashMap.containsKey(key))
                return true;
            case 2:if(!hashMap.containsKey(key))
                return true;
            case 3:if(hashMap.containsKey(key))
                return true;
        }
        return false;
    }
}
