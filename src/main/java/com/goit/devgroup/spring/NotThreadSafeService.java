package com.goit.devgroup.spring;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class NotThreadSafeService {
    public int sum(int a, int b) {
        int result = a + b;

        try {
            Thread.sleep(5);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) {
        NotThreadSafeService service = new NotThreadSafeService();

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            Thread t = new Thread(() -> {
                int a = new Random().nextInt(10000);
                int b = new Random().nextInt(10000);
                int expected = a + b;

                int actual = service.sum(a, b);

                if (actual != expected) {
                    System.out.println(
                            "Invalid calculation, should be " + expected +
                                    " but was " + actual
                    );
                } else {
                    System.out.println("It's okay");
                }
            });

            threads.add(t);
        }

        for (Thread thread : threads) {
            thread.start();
        }

    }
}
