import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReEntrantLock {
    private static int counter = 0;
    private static Lock lock =new ReentrantLock(true);
    public static void increment() {
        lock.lock();
        try {
            for(int i=0; i<100000;i++) {
                counter++;
            }
        } finally {
            lock.unlock();
        }
    }
    public static void main(String args[]) throws InterruptedException {
        Thread T1 = new Thread((Runnable) () -> {
            increment();
        });
        Thread T2 = new Thread((Runnable) () -> {
           increment();
        });
        T1.start();
        T2.start();
        T1.join();
        T2.join();
        System.out.println(counter);
    }
}
