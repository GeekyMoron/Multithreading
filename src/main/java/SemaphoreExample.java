import java.util.concurrent.*;
import java.util.stream.IntStream;

enum Downloader {
    INSTANCE;
    // permits are the number of time the variable will be used concurrently by a thread
    private Semaphore  semaphore =  new Semaphore(3, true);
    public void download() throws InterruptedException {
        semaphore.acquire();
        downloadingData();
        semaphore.release();
    }

    private void downloadingData() throws InterruptedException {
    System.out.println("downloading data from web");
    Thread.sleep(2000);
    }
}
public class SemaphoreExample {

    public static void main(String args[]) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        IntStream.range(0,10).forEach(i -> {
         executorService.execute( () -> {
             try {
                 Downloader.INSTANCE.download();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         });
        });
    }
}
