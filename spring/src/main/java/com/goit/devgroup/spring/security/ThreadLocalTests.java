package com.goit.devgroup.spring.security;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadLocalTests {
    private static MyThreadLocal name = new MyThreadLocal();

    public static void main(String[] args) throws InterruptedException {
        name.set("main");

        Thread threadA = new Thread(() -> {
            name.set("threadA");
            System.out.println("name.get() = " + name.get());
        });
        threadA.start();

        Thread.sleep(1000);
        System.out.println("name.get() = " + name.get());
    }

    public static class MyThreadLocal {
        private Map<Long, String> values = new ConcurrentHashMap<>();

        public String get() {
            return values.get(Thread.currentThread().getId());
        }

        public void set(String value) {
            values.put(Thread.currentThread().getId(), value);
        }
    }
}
