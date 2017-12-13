package thread_example;

public class DataHolder {

    private int nextId;

    public int getPrevId() {
        synchronized(this) {
            return nextId--;
        }
    }

    public synchronized int getNextId() {
        return nextId++;
    }
}
