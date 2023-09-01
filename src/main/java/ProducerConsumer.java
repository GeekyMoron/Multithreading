import java.util.ArrayList;
import java.util.List;

public class ProducerConsumer {
    public static void main (String[] args) {
        Runner r = new Runner();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    r.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    r.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
    }
}
class Runner {
    private List<Integer> list = new ArrayList<>();
    private static final int Upper_Limit = 5;
    private static final int Lower_Limit = 0;
    private static int value = 0;
    public  void producer() throws InterruptedException {
        synchronized (this) {
            while(true) {
                if(list.size() == Upper_Limit) {
                    System.out.println("waiting for object removal");
                    Thread.sleep(5000);
                    wait();
                }
                else {
                    System.out.println("value added" + value);
                    Thread.sleep(5000);
                    list.add(value);
                    value++;
                    notify();
                }
            }
        }
    }
    public void consumer() throws InterruptedException {
        synchronized (this) {
            while(true) {
                if(list.size() == Lower_Limit) {
                    System.out.println("waiting for object addition");
                    Thread.sleep(5000);
                    value = 0;
                    wait();
                }
                else {
                    System.out.println("value deleted" + list.get(list.size()-1));
                    Thread.sleep(5000);
                    list.remove(list.size()-1);
                    notify();
                }
            }
        }
    }
}
