package DiningPhilosopher;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {
    @Override
    public String toString() {
        return "Chopstick{" +
            "id=" + id +
            ", lock=" + lock +
            '}';
    }

    private int id;

    public Chopstick(int id) {
        this.id = id;
        this.lock = new ReentrantLock();
    }

    private Lock lock;

    public Boolean PutUp(Philosopher philosopher, State state) throws InterruptedException {
        if(lock.tryLock(10, TimeUnit.MILLISECONDS)) {
            System.out.println("chopstick acquired by: " + philosopher + state.toString() + " " + this);
            return true;
        }
        return false;
    }

    public void putDown(Philosopher philosopher, State state) {
        lock.unlock();
        System.out.println("chopstick released by: "  + philosopher + state.toString() +" " + this);
    }
}
