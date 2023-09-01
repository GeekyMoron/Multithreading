import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.concurrent.Exchanger;

//exchanger is used to exchange data between two threads
@AllArgsConstructor
class FirstThread implements Runnable {

    private int counter;
    private Exchanger<Integer> exchanger;
    @SneakyThrows
    @Override
    public void run() {
     while(true) {
         counter ++;
         System.out.println("first one is down");
         counter = exchanger.exchange(counter);
     }
    }
}
@AllArgsConstructor
class SecondThread implements Runnable {

    private int counter;
    private Exchanger<Integer> exchanger;
    @SneakyThrows
    @Override
    public void run() {
        while(true) {
            counter --;
            System.out.println("second one is down");
            counter = exchanger.exchange(counter);
        }
    }
}
public class ExchangerExample {
    public static void main(String args[]) {
        Thread t1= new Thread(new FirstThread(0,new Exchanger<>()));
        Thread t2= new Thread(new SecondThread(-1,new Exchanger<>()));
        t1.start();
        t2.start();
    }
}
