import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@AllArgsConstructor
class Worker implements Runnable{
    private int id;
    private CountDownLatch countDownLatch;

    @SneakyThrows
    @Override
    public void run() {
        doWork();
        countDownLatch.countDown();
    }

    private void doWork() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("Thread is working with id:" + id);
    }
}

public class Latch {
    public  static void main(String args[]) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        ExecutorService service = Executors.newFixedThreadPool(10);
        IntStream.range(1,10).forEach(id -> service.execute(new Worker(id,latch)));
        latch.await();// it will block main thread and let  all threads to complete its task then proceed to next part of code
        // whereas cyclic barrier will block all threads to wait for each other
        System.out.println("to the next part of code");
        service.shutdown();
    }
}
