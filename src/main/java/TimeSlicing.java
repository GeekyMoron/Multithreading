public class TimeSlicing{
public static void main(String args[]) throws InterruptedException {
    System.out.println(Thread.currentThread().getName());
    Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
    System.out.println(Thread.currentThread().getPriority());
    Thread T1 = new Thread((Runnable) () -> {
        for(int i=0;i<10;i++)
        {
            System.out.println("Thread 1 :" + i);
        }
    });
    Thread T2 = new Thread((Runnable) () -> {
        for(int i=0;i<10;i++)
        {
            System.out.println("Thread 2 :"  + i);
        }
    });
    Thread T3 = new Thread((Runnable) () -> {
        while(true)
        {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Jaldi wahan se hato");
        }
    });
    T3.setDaemon(true);
    T3.start();
    // we can wait for a particular thread to finish its execution before starting another with the help of join method
    T1.start();
    T1.join();
    T2.start();
    //Daemon threads are the low priority threads(like GC or NFC or bluetooth) that are shut down at last by jvm after worker threads.
    //we make a daemon thread if we wanted to run it in Background
}
}
