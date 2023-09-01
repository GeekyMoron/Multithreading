import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.UUID;
import java.util.concurrent.*;
import java.util.stream.IntStream;

@AllArgsConstructor
class Worker2 implements Runnable{
    private int id;
    private CyclicBarrier cyclicBarrier;
    private UUID uuid;

    @SneakyThrows
    @Override
    public void run() {
        doWork();
    }

    private void doWork() throws InterruptedException, BrokenBarrierException {
        Thread.sleep(3000);
        System.out.println("Thread is working with id:" + id + uuid);
        cyclicBarrier.await(); // threads have to wait not main thread.
        System.out.println("All done");
    }
}
public class CyclicBarrierEX {
    public  static void main(String args[]) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
        ExecutorService service = Executors.newFixedThreadPool(10);
        IntStream.range(0,10).forEach(id -> service.execute(new Worker2(id,cyclicBarrier,new UUID(1,3))));
        System.out.println("into the main");
        service.shutdown();
    }
}
