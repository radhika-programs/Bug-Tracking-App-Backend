package com.bugtracking;

class SwapExample {

    static final Object lock1 = new Object();
    static final Object lock2 = new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
            	//lock1.notify();
            	
                System.out.println("Thread-1 acquired lock1");
                try { //Thread.sleep(100); 
                } catch (Exception e) {}
                synchronized (lock2) {
                    System.out.println("Thread-1 acquired lock2");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread-2 acquired lock2");
                try { //Thread.sleep(100); 
                } catch (Exception e) {}
                synchronized (lock1) {
                    System.out.println("Thread-1 acquired lock2");
                }
            }
        });

        t1.start();
        t2.start();
    }
}
