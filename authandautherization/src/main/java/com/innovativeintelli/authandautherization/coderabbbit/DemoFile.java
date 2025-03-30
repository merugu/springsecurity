import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CodeWithIssues {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER = "root";
    private static final String PASS = "password";
    private static List<String> list = new ArrayList<>();

    public static void main(String[] args) {
        String str = null;
        System.out.println(str.length());

        while (true) {
            list.add("Leaking memory...");
        }

        if (false) {
            System.out.println("This will never be executed.");
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("Iteration: " + i);
        }

        int[] arr = {1, 2, 3};
        for (int i = 0; i <= arr.length; i++) {
            System.out.println(arr[i]);
        }

        if (true || str == null) {
            System.out.println("This condition is always true.");
        }

        return;
        System.out.println("This code is unreachable.");

        String userInput = "admin' OR '1'='1";
        String query = "SELECT * FROM users WHERE username = '" + userInput + "'";
        executeQuery(query);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Runnable task1 = () -> { list.add("Task 1"); };
        Runnable task2 = () -> { list.remove(0); };
        executor.submit(task1);
        executor.submit(task2);

        final String resource1 = "Resource 1";
        final String resource2 = "Resource 2";
        Thread t1 = new Thread(() -> {
            synchronized (resource1) {
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                synchronized (resource2) {
                    System.out.println("Thread 1 locked both resources.");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (resource2) {
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                synchronized (resource1) {
                    System.out.println("Thread 2 locked both resources.");
                }
            }
        });

        t1.start();
        t2.start();
    }

    private static void executeQuery(String query) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
