package DiningPhilosopher;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {
    public static void main(String[] args) {
        ExecutorService executorService = null;
        Philosopher[] philosopher = null;
        Chopstick[] chopsticks = null;
        try {
            philosopher = new Philosopher[Constants.NO_OF_PHILOSOPHERS];
            chopsticks = new Chopstick[Constants.NO_OF_CHOPSTICKS];
            for (int i=0; i<Constants.NO_OF_CHOPSTICKS;i++) {
                chopsticks [i] = new Chopstick(i);
            }
            executorService = Executors.newFixedThreadPool(Constants.NO_OF_PHILOSOPHERS);
            for (int i=0; i<Constants.NO_OF_PHILOSOPHERS;i++) {
                philosopher[i] = new Philosopher(i, chopsticks[i], chopsticks[(i+1)%Constants.NO_OF_PHILOSOPHERS]);
                executorService.execute(philosopher[i]);
            }
            Thread.sleep(Constants.SIMULATION_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
          executorService.shutdown();
          Arrays.stream(philosopher).forEach(philosopher1 -> {
              philosopher1.setFull(true);
          });
        }
    }
}
