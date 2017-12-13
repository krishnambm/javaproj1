package thread_example;

public class RunnableExample {
    public static void main(String[] args) throws InterruptedException {
        RunnableExample td = new RunnableExample();
        td.go();
    }

    public void go() throws InterruptedException {
        Worker w1 = new Worker("Worker 1");
        Worker w2 = new Worker("Worker 2");
        Thread th1 = new Thread(w1);
        Thread th2 = new Thread(w2);

        th1.start();
        th2.start();
        th1.join();
        th2.join();
        double result = w1.getSum() + w2.getSum();
        System.out.println("Result: " + result);

    }

    class Worker implements Runnable {
        private String name;
        private double sum;

        Worker(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i =0; i < 1000; i++) {
                System.out.println("Running thread: " + name);
                sum += i;
            }

        }

        public double getSum() {
            return sum;
        }
    }
}
