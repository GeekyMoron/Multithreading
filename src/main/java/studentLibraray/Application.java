package studentLibraray;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {
    public static void main(String[] args) {
        ExecutorService executorService = null;
        Student[] students = null;
        Book[] books = null;
        try {
            students = new Student[Constants.numberOfStudents];
            books = new Book[Constants.numberOfBooks];
            for (int i = 0; i< Constants.numberOfBooks; i++) {
                books [i] = new Book(i);
            }
            executorService = Executors.newFixedThreadPool(Constants.numberOfStudents);
            for (int i = 0; i< Constants.numberOfStudents; i++) {
                students[i] = new Student(books[i],i);
                executorService.execute(students[i]);
            }
        } finally {
            executorService.shutdown();
        }
    }
}
