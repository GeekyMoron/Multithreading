package studentLibraray;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Book {
    private int id;

    @Override
    public String toString() {
        return "Book{" +
            "id=" + id +
            '}';
    }

    private static Lock lock;

    public Book(int id) {
        this.id = id;
        this.lock =  new ReentrantLock();
    }
    public boolean pickBook(Student student) throws InterruptedException {
        if(lock.tryLock(20, TimeUnit.MILLISECONDS)) {
            System.out.println(student + "reading book" + this);
            Thread.sleep(2000);
            return true;
        }
        return false;
    }
    public void putBookDown(Student student) {
        lock.unlock();
        System.out.println(student + "stops book reading" + this);
    }
}
