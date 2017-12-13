package thread_example;

public class SharedDataHolder {
    public static void main(String[] args) throws InterruptedException {
        SharedDataHolder td = new SharedDataHolder();
        td.go();
    }

    public void go() throws InterruptedException {
        DataHolder dh = new DataHolder();
        Worker w1 = new Worker("Worker 1", dh);
        Worker w2 = new Worker("Worker 2", dh);
        Thread th1 = new Thread(w1);
        Thread th2 = new Thread(w2);

        th1.start();
        th2.start();
        th1.join();
        th2.join();
        double result = w1.getSum() + w2.getSum();

        System.out.println("Result: " + result);
        System.out.println("DataHolder.id " + dh.getNextId());
    }

    class Worker implements Runnable {
        private String name;
        private double sum;
        private DataHolder dataHolder;

        Worker(String name, DataHolder dh) {
            this.name = name;
            this.dataHolder = dh;
        }

        @Override
        public void run() {
            for (int i =0; i < 1000; i++) {
                dataHolder.getNextId();
                System.out.println("Running thread: " + name);
                sum += i;
            }

        }

        public double getSum() {
            return sum;
        }
    }
}
