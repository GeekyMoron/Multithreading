package studentLibraray;

import lombok.SneakyThrows;

public class Student  implements Runnable{
    private Book book;
    private  int id;

    public Student(Book book, int id) {
        this.book = book;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
            "id=" + id +
            '}';
    }

    @SneakyThrows
    @Override
    public void run() {
        while(true) {
            waiting();
            if (book.pickBook(this)) {
                System.out.println(this + "reading");
                book.putBookDown(this);
            }
        }
    }

    public void waiting() throws InterruptedException {
        Thread.sleep(10000);
        System.out.println(this + "is waiting for his turn to take book");
    }
}
