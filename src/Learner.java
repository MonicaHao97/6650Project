public class Learner extends KeyValueStore implements Runnable{


    public String commit(int key, int reqType){
        String response = "";

        switch(reqType) {
            case 1: response = super.get(key);
                break;
            case 2: response = super.put(key);
                break;
            case 3: response = super.delete(key);
                break;
        }
        return response;
    }

    public void start(){}

    @Override
    public void run() {}
}