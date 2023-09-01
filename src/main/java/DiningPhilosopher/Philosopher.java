package DiningPhilosopher;

import lombok.SneakyThrows;

import java.util.Random;

public class Philosopher implements Runnable {
    private int id;
    private volatile boolean full;
    private Chopstick leftChopStick;
    private Chopstick rightChopStick;
    private Random random;
    private int eatingCounter;

    public boolean isFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
    }

    @Override
    public String toString() {
        return "Philosopher{" +
            "id=" + id +
            '}';
    }

    public Philosopher(int id, Chopstick leftChopStick, Chopstick rightChopStick) {
        this.id = id;
        this.leftChopStick = leftChopStick;
        this.rightChopStick = rightChopStick;
        this.random = new Random();
    }

    @SneakyThrows
    @Override
    public void run() {
        while(!isFull()) {
            think();
            if (leftChopStick.PutUp(this, State.LEFT) && leftChopStick.PutUp(this, State.RIGHT)) {
                eat();
                leftChopStick.putDown(this,State.LEFT);
                rightChopStick.putDown(this,State.RIGHT);
            }
        }
    }

    public void eat() throws InterruptedException {
     System.out.println(this + "is eating...");
     eatingCounter ++;
     Thread.sleep(random.nextInt(1000));
    }

    public void think() throws InterruptedException {
        System.out.println(this + "is thinking...");
        Thread.sleep(random.nextInt(1000));
    }
}
