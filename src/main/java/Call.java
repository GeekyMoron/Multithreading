import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class Processor implements Callable<String> {
    public Processor(int id) {
        this.id = id;
    }

    private int id;

    @Override
    public String call() throws Exception {
        Thread.sleep(2000);
        return "Id" + id;
    }
}

public class Call {
    public static void main (String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Future<String>> futureList = new ArrayList<>();
         for (int i=0; i<10000; i++) {
             Future<String> future = executorService.submit(new Processor(i+1));
             futureList.add(future);
         }
        futureList.stream().forEach(future->{
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}
